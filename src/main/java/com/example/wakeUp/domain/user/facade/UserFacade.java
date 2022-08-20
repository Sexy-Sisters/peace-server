package com.example.wakeUp.domain.user.domain.facade;

import com.example.wakeUp.domain.user.domain.exception.UserAlreadyExistsException;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public void validateSignUp(String email) {
        if (userRepository.existsByEmail(email)) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }
}
