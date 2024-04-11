package com.khoavm.lifeup.module.user.service;

import com.khoavm.lifeup.module.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {
    UserDto getUserDetailByUsernameOrEmailOrPhone(String text);
    UserDto getUserDetailById(UUID id);
    UserDto signUp(UserDto userDto);
}
