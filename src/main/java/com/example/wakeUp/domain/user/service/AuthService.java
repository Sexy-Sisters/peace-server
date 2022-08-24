package com.example.wakeUp.domain.user.service;

import com.example.wakeUp.domain.user.controller.dto.request.LoginRequestDto;
import com.example.wakeUp.domain.user.controller.dto.response.TokenResponseDto;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.facade.UserFacade;
import com.example.wakeUp.global.config.redis.RedisService;
import com.example.wakeUp.global.security.jwt.JwtProperties;
import com.example.wakeUp.global.security.jwt.JwtTokenProvider;
import com.example.wakeUp.global.security.jwt.JwtValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RedisService redisService;
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtValidateService jwtValidateService;

    @Transactional
    public TokenResponseDto login(LoginRequestDto request) {
        User user = userFacade.findByEmail(request.getEmail());
        userFacade.checkPassword(user, request.getPassword());

        final String accessToken = jwtTokenProvider.createAccessToken(user.getEmail());
        final String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

        redisService.setDataExpire(request.getEmail(), refreshToken,  Duration.ofMillis(JwtProperties.REFRESH_TOKEN_VALID_TIME));

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public void logout(HttpServletRequest request) {

        String accessToken = jwtTokenProvider.resolveToken(request);
        Date expiration = jwtTokenProvider.getExpiredTime(accessToken);
        redisService.setBlackList(accessToken, "logout", expiration.getTime());

        User user = userFacade.getCurrentUser();
        redisService.delete(user.getEmail());
    }

    @Transactional
    public TokenResponseDto getNewAccessToken(String refreshToken) {
        jwtValidateService.validateRefreshToken(refreshToken);

        return TokenResponseDto.builder()
                .accessToken(
                        jwtTokenProvider.createAccessToken(
                                jwtValidateService.getEmail(refreshToken)
                        )
                )
                .build();
    }
}
