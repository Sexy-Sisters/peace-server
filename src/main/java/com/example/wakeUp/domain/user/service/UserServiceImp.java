package com.example.wakeUp.domain.user.domain.service;

import com.example.wakeUp.domain.user.domain.controller.dto.request.CreateUserRequestDto;

public interface UserServiceImp {

    void signUp(CreateUserRequestDto request);
}
