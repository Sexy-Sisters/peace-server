package com.example.wakeUp.domain.user.facade;

import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.exception.*;
import com.example.wakeUp.global.redis.RedisService;
import com.example.wakeUp.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    public void validateSignUp(String email, String name, @NotNull(message = "닉네임을 입력해주세요") String nickName) {
        if (
                userRepository.existsByEmail(email) ||
                userRepository.existsByName(name) ||
                userRepository.existsByNickName(nickName)
        ) {
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

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void validateUpdateNickName(String nickName) {
        if(userRepository.existsByNickName(nickName)) {
            throw NickNameAlreadyExistsException.EXCEPTION;
        }
    }
}
