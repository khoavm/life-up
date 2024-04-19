package com.khoavm.lifeup.module.user.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.khoavm.lifeup.config.context.Context;
import com.khoavm.lifeup.exception.NotFoundException;
import com.khoavm.lifeup.infra.redis.RedisService;
import com.khoavm.lifeup.config.security.GoogleUser;
import com.khoavm.lifeup.config.security.JwtAdditionalClaim;
import com.khoavm.lifeup.config.security.OAuth2Provider;
import com.khoavm.lifeup.module.user.dto.UserDto;
import com.khoavm.lifeup.module.user.entity.User;
import com.khoavm.lifeup.module.user.entity.UserCredential;
import com.khoavm.lifeup.module.user.mapper.UserMapper;
import com.khoavm.lifeup.module.user.repository.UserCredentialRepository;
import com.khoavm.lifeup.module.user.repository.UserRepository;
import com.khoavm.lifeup.util.JwtTokenUtil;
import com.khoavm.lifeup.util.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final ObjectMapper mapper;
    private HttpServletRequest request;
    private final Context context;
    private final RedisService redisService;
    @Override
    public UserDto getUserDetailByUsernameOrEmailOrPhone(String text) {
        var userInfo = userRepository.findByUsernameOrEmailOrPhone(text, text, text)
                .orElseThrow(() -> new UsernameNotFoundException("not found user name"));

        return UserMapper.UserToDto(userInfo);
    }

    @Override
    public UserDto getUserDetailById(UUID id) {
        var userInfo = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("not found user name"));

        return UserMapper.UserToDto(userInfo);
    }

    @Override
    public UserDto signUp(UserDto createUser) {
        createUser.setId(UUID.randomUUID());
        createUser.setCreatedAt(OffsetDateTime.now());
        var hashPassword = passwordEncoder.encode(createUser.getPassword());
        createUser.setPassword(hashPassword);
        var newUser = userRepository.save(UserMapper.UserFromDto(createUser));
        return UserMapper.UserToDto(newUser);
    }

    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public String handleOauth2Callback(Authentication token, OAuth2Provider provider) {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) token;

        switch (provider){
            case GOOGLE -> {
                return handleGoogleCallBack(oAuth2AuthenticationToken);
            }
            default -> throw new IllegalStateException("Unexpected value: " + provider);
        }


    }

    @Override
    public UserDto getUserDetail() {
        return getUserDetailById(context.getUserId());
    }

    @Override
    public void logout() {
        String jwt = request.getHeader("Authorization");
        if (jwt == null || jwt.isEmpty()){
            throw new NotFoundException("not found jwt token");
        }
        jwt = jwt.replace("Bearer ", "");
        redisService.addBlackListToken(jwt);

    }

    private String handleGoogleCallBack(OAuth2AuthenticationToken token){
        var oAuth2UserAttribute = token.getPrincipal().getAttributes();
        GoogleUser googleUser = mapper.convertValue(oAuth2UserAttribute, GoogleUser.class);
        var existUser = userRepository.findByEmail(googleUser.getEmail())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setId(UUID.randomUUID());
                    newUser.setEmail(googleUser.getEmail());
                    newUser.setUsername(CommonUtil.generateSecureRandomString(10));
                    return userRepository.save(newUser);
                });
        userCredentialRepository.findByProviderAndUser_Id(OAuth2Provider.GOOGLE, existUser.getId())
                .orElseGet(() -> {
                    UserCredential newUserCredential = new UserCredential();
                    newUserCredential.setId(UUID.randomUUID());
                    newUserCredential.setProvider(OAuth2Provider.GOOGLE);
                    newUserCredential.setUser(existUser);
                    newUserCredential.setProviderId(googleUser.getSub());

                    return userCredentialRepository.save(newUserCredential);
                });
        JwtAdditionalClaim jwtAdditionalClaim = new JwtAdditionalClaim(existUser.getId(), existUser.getUsername());
        return jwtTokenUtil.genToken(jwtAdditionalClaim);
    }


}
