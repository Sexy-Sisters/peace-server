package com.example.wakeUp.global.security.jwt;

import com.example.wakeUp.global.config.redis.RedisService;
import com.example.wakeUp.global.security.jwt.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidateService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public String getEmail(String token) {
        return jwtTokenProvider.extractAllClaims(token)
                .get("email", String.class);
    }

    public void validateRefreshToken(String token) {
        if (redisService.getData(getEmail(token)) == null) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}
