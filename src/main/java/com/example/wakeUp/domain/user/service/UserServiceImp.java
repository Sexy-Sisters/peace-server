package com.example.wakeUp.domain.user.service;

import com.example.wakeUp.domain.user.controller.dto.request.CreateUserRequestDto;

public interface UserServiceImp {

    void signUp(CreateUserRequestDto request);
}
