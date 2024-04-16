package com.khoavm.lifeup.module.common.dto;

import org.springframework.lang.NonNull;

public record Query(@NonNull String field,@NonNull String value) {
}
