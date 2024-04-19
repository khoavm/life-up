package com.khoavm.lifeup.config.security;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;



public record JwtAdditionalClaim(@NonNull UUID userId, @NonNull String userName)  {
}