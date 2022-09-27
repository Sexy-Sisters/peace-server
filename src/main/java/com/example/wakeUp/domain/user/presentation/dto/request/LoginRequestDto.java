package com.example.wakeUp.domain.user.presentation.dto.request;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
