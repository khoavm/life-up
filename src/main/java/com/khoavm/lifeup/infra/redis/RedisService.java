package com.khoavm.lifeup.infra.redis;

public interface RedisService {
    void addBlackListToken(String token);
    boolean isTokenInBlackList(String token);
}
