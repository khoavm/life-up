package com.khoavm.lifeup.module.user.service;


import com.khoavm.lifeup.module.user.dto.UserDto;
import com.khoavm.lifeup.module.user.mapper.UserMapper;
import com.khoavm.lifeup.module.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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
}
