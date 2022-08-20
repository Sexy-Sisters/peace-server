package com.example.wakeUp.domain.user.facade;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.exception.CodeMismatchException;
import com.example.wakeUp.domain.user.exception.UserAlreadyExistsException;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.exception.UserNotFoundException;
import com.example.wakeUp.global.config.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final RedisService redisService;

    public void validateSignUp(String email) {
        if (userRepository.existsByEmail(email)) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }

    // TODO :: change real getCurrentUser
    public User getFakeCurrentUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }


    public void checkCode(String code) {

        String email = getFakeCurrentUser().getEmail();

        if(!redisService.getData(email).equals(code)) {
            throw CodeMismatchException.EXCEPTION;
        }

        redisService.delete(email);
    }
}
