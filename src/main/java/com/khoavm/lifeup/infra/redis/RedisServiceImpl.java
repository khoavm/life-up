package com.khoavm.lifeup.infra.redis;

import com.khoavm.lifeup.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final TokenBlackListRepository tokenBlackListRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void addBlackListToken(String token) {
        var payload = jwtTokenUtil.parseJwtToken(token);
        var exp = payload.getExpiration().getTime() - new Date().getTime();
        var blockToken = new TokenBlackList();
        blockToken.setToken(token);
        blockToken.setTimeToLive(exp);
        tokenBlackListRepository.save(blockToken);
    }

    @Override
    public boolean isTokenInBlackList(String token) {
        return tokenBlackListRepository.existsByToken(token);
    }
}
