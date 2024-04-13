package com.khoavm.lifeup.config.filter;

import com.khoavm.lifeup.config.security.Context;
import com.khoavm.lifeup.module.common.dto.JwtAdditionalClaim;
import com.khoavm.lifeup.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Component
public class JwtGeneratorFilter extends OncePerRequestFilter {

    JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        JwtAdditionalClaim jwtAdditionalClaim = new JwtAdditionalClaim(Context.getUserId(), Context.getUserName());
        var jwt = jwtTokenUtil.genToken(jwtAdditionalClaim);
        response.setHeader("Authorization", jwt);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
       return !request.getServletPath().endsWith("/login");
    }
}
