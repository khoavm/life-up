package com.khoavm.lifeup.filter;

import com.khoavm.lifeup.exception.UnAuthenticatedException;
import com.khoavm.lifeup.infra.redis.RedisService;
import com.khoavm.lifeup.config.security.AuthenticationDetail;
import com.khoavm.lifeup.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@AllArgsConstructor
@Component
public class JWTValidatorFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final RedisService redisService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String jwt = request.getHeader("Authorization");
        if (jwt == null || jwt.isEmpty()){
            chain.doFilter(request, response);
            return;
        }
        jwt = jwt.replace("Bearer ", "");
        if (redisService.isTokenInBlackList(jwt)){
            throw new UnAuthenticatedException("User has logged out");
        }
        try {

            var claims = jwtTokenUtil.parseJwtToken(jwt);
            String username = String.valueOf(claims.get("user"));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                    null);
            AuthenticationDetail authenticationDetail = new AuthenticationDetail()
                    .userId(UUID
                            .fromString(String
                                    .valueOf(claims.get("id"))));

            auth.setDetails(authenticationDetail);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            throw new UnAuthenticatedException("Invalid Token received!");
        }

        chain.doFilter(request, response);
    }


    @Override protected boolean shouldNotFilter(HttpServletRequest request) {

        return Stream.of("/user/login", "/user/sign-up", "/user/test").anyMatch(path -> request.getServletPath().endsWith(path));
    }


}
