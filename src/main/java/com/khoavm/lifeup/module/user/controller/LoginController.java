package com.khoavm.lifeup.module.user.controller;


import com.khoavm.lifeup.config.security.Context;
import com.khoavm.lifeup.module.common.dto.ResponseDto;
import com.khoavm.lifeup.module.user.service.UserService;
import com.khoavm.lifeup.util.JwtTokenUtil;
import com.khoavm.lifeup.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
@RequiredArgsConstructor
public class LoginController {

    @Value("${client.url}")
    private String clientUrl;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto> login(){
        try {
            var userInfo = userService.getUserDetailById(Context.getUserId());
            return ResponseUtil.DefaultCreateSuccessResponse(userInfo);
        }catch (Exception e){System.out.println(e);
        }
        return null;
    }




}
