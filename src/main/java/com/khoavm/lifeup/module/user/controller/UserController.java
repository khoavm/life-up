package com.khoavm.lifeup.module.user.controller;


import com.khoavm.lifeup.module.user.service.UserService;
import com.khoavm.lifeup.util.ResponseUtil;
import com.khoavm.lifeup.module.common.dto.ResponseDto;
import com.khoavm.lifeup.module.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    @GetMapping("/test")
    public ResponseEntity<ResponseDto> createHabit(){
        return ResponseUtil.DefaultCreateSuccessResponse("ok");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(@RequestBody UserDto userDto){
        var user = userService.signUp(userDto);
        return ResponseUtil.DefaultCreateSuccessResponse(user);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(Principal authentication){
        try {
            var userInfo =  userService.getUserDetailByUsernameOrEmailOrPhone(authentication.getName());
            return ResponseUtil.DefaultCreateSuccessResponse(userInfo);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
