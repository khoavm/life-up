package com.khoavm.lifeup.module.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.khoavm.lifeup.module.user.entity.User}
 */

@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto implements Serializable {
    UUID id;
    String username;
    String email;
    String phone;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    Instant createdAt;
}