package com.example.wakeUp.global.security.jwt;

public class JwtProperties {
//    public final static long ACCESS_TOKEN_VALID_TIME = 30 * 60 * 1000L;
    public final static long ACCESS_TOKEN_VALID_TIME = 1000L;
    public final static long REFRESH_TOKEN_VALID_TIME = 30 * 24 * 60 * 60 * 1000L;
    public final static String JWT_PREFIX = "Bearer";
    public final static String JWT_HEADER = "Authorization";
}
