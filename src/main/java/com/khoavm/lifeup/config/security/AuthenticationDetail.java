package com.khoavm.lifeup.config.security;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true, fluent = true)
public class AuthenticationDetail {
    private UUID userId;
}
