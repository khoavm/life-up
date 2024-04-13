package com.khoavm.lifeup.module.common.dto;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;



public record JwtAdditionalClaim(@NonNull UUID userId, @NonNull String userName)  {
}