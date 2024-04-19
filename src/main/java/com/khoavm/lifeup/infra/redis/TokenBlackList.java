package com.khoavm.lifeup.infra.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@RedisHash
class TokenBlackList {
    @Id
    private String token;

    @TimeToLive(unit= TimeUnit.MILLISECONDS)
    private long timeToLive;
}
