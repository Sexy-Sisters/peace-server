package com.example.wakeUp.domain.user.facade;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.exception.*;
import com.example.wakeUp.global.config.redis.RedisService;
import com.example.wakeUp.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    public void validateSignUp(String email, String name) {
        if (userRepository.existsByEmail(email) || userRepository.existsByName(name)) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }

    public User getCurrentUser() {
        AuthDetails userDetails = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public String securityUtil() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth.getUser().getEmail();
    }
    public void checkCode(String code, String email) {

        String findCode = redisService.getData(email);

        if (findCode == null) {
            throw ExpiredDataException.EXCEPTION;
        }
        else if(!findCode.equals(code)) {
            throw CodeMismatchException.EXCEPTION;
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void checkPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw PasswordMisMatchException.EXCEPTION;
    }
}
