package com.khoavm.lifeup.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;


@Component
public class JwtTokenUtil {


    @Value("${jwt.secret}")
    private String secret;

    public  String genToken(Map<String, Object> claim){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .claims(claim)
                .issuer("Life Up")
                .subject("JWT Token")
                .expiration(new Date((new Date()).getTime() + 3600000))
                .signWith(key).compact();
    }

}
