package com.example.wakeUp.global.security.jwt;

import com.example.wakeUp.global.config.redis.RedisService;
import com.example.wakeUp.global.security.jwt.exception.AlreadyLogoutException;
import com.example.wakeUp.global.security.jwt.exception.ExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final RedisService redisService;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String email) {
        return createToken(email, JwtProperties.ACCESS_TOKEN_VALID_TIME);
    }

    public String createRefreshToken(String email) {
        return createToken(email, JwtProperties.REFRESH_TOKEN_VALID_TIME);
    }

    public String createToken(String email, long time) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            if (redisService.hasBlackList(token)) {
                throw AlreadyLogoutException.EXCEPTION;
            }
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        }
    }

    public Date getExpiredTime(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(JwtProperties.JWT_HEADER);
        return parseToken(bearer);
    }

    private String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(JwtProperties.JWT_PREFIX))
            return bearerToken.replace(JwtProperties.JWT_PREFIX, "");
        return null;
    }
}
