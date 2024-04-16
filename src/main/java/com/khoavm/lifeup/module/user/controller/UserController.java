package com.khoavm.lifeup.module.user.controller;


import com.khoavm.lifeup.module.user.service.UserService;
import com.khoavm.lifeup.util.ResponseUtil;
import com.khoavm.lifeup.module.common.dto.ResponseDto;
import com.khoavm.lifeup.module.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    @GetMapping("/test")
    public ResponseEntity<ResponseDto<String>> createHabit(){
        return ResponseUtil.Created("ok");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto<UserDto>> signUp(@RequestBody UserDto userDto){
        var user = userService.signUp(userDto);
        return ResponseUtil.Created(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<UserDto>> login() {
        var userInfo = userService.getUserDetail();
        return ResponseUtil.Ok(userInfo);
    }


}
