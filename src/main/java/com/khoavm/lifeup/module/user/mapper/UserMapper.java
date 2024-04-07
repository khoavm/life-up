package com.khoavm.lifeup.module.user.mapper;

import com.khoavm.lifeup.module.user.dto.UserDto;
import com.khoavm.lifeup.module.user.entity.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class UserMapper {
    public static UserDto UserToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                //.password(user.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .build();
    }
    public static User UserFromDto(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setCreatedAt(dto.getCreatedAt());
        return user;
    }
}
