package com.khoavm.lifeup.module.user.service;

import com.khoavm.lifeup.module.user.dto.UserDto;
import org.springframework.stereotype.Service;

public interface UserService {
    UserDto getUserDetailByUsernameOrEmailOrPhone(String text);
    UserDto signUp(UserDto userDto);
}
