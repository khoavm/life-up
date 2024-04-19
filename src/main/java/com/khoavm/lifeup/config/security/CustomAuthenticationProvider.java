package com.khoavm.lifeup.config.security;

import com.khoavm.lifeup.module.user.entity.User;
import com.khoavm.lifeup.module.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<User> userInfo = userRepository.findByUsernameOrEmailOrPhone(username, username, username);
        if (userInfo.isEmpty()) {
            throw new BadCredentialsException("user not found");
        }
        if (!passwordEncoder.matches(pwd,userInfo.get().getPassword())){
            throw new BadCredentialsException("user credential is not correct");
        }

        var newAuthentication =  new UsernamePasswordAuthenticationToken(username, pwd);
        AuthenticationDetail authenticationDetail = new AuthenticationDetail();
        authenticationDetail.userId(userInfo.get().getId());
        newAuthentication.setDetails(authenticationDetail);

        return newAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
