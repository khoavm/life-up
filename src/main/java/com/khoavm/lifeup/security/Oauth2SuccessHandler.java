package com.khoavm.lifeup.security;

import com.khoavm.lifeup.module.common.dto.OAuth2Provider;
import com.khoavm.lifeup.module.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@RequiredArgsConstructor
@Component
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Value("${client.url}")
    private String clientUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2Provider provider = null;
        if (request.getServletPath().contains("google")){
            provider = OAuth2Provider.GOOGLE;
        }

        var jwt = userService.handleOauth2Callback(authentication , provider);

        response.sendRedirect(String.format("%s?token=%s", clientUrl, jwt));
    }
}
