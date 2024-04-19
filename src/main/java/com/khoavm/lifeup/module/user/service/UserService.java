package com.khoavm.lifeup.module.user.service;

import com.khoavm.lifeup.config.security.OAuth2Provider;
import com.khoavm.lifeup.module.user.dto.UserDto;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface UserService {
    UserDto getUserDetailByUsernameOrEmailOrPhone(String text);
    UserDto getUserDetailById(UUID id);
    UserDto signUp(UserDto userDto);
    String handleOauth2Callback(Authentication token, OAuth2Provider provider);
    UserDto getUserDetail();

    void logout();
}
