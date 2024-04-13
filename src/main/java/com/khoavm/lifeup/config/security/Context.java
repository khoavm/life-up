package com.khoavm.lifeup.config.security;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class Context {
    private final static SecurityContext context = SecurityContextHolder.getContext();


    public static String getUserName() {
        return context.getAuthentication().getName();
    }

    public static UUID getUserId() {
        var authDetail = context.getAuthentication().getDetails();
        if (authDetail instanceof AuthenticationDetail){
            return ((AuthenticationDetail) authDetail).userId();
        }
        return new UUID(0L, 0L);
    }
}
