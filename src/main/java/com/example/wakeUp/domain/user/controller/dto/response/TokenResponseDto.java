package com.example.wakeUp.domain.user.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
