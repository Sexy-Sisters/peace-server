package com.example.wakeUp.domain.user.domain.service;

import com.example.wakeUp.domain.user.domain.controller.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.domain.facade.UserFacade;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImp{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Override
    @Transactional
    public void signUp(CreateUserRequestDto request) {
        userFacade.validateSignUp(request.getEmail());

        userRepository.save(request.toEntity(passwordEncoder));
    }
}
