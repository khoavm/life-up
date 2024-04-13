package com.khoavm.lifeup.module.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleUser {
    private String atHash;
    private String sub;
    private String emailVerified;
    private String iss;
    private String givenName;
    private String nonce;
    private String picture;
    private List<String> aud;
    private String azp;
    private String name;
    private Instant exp;
    private String familyName;
    private String iat;
    private String email;

}
