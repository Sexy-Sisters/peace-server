package com.example.wakeUp.domain.user.service;

import com.example.wakeUp.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.facade.UserFacade;
import com.example.wakeUp.global.Utils.RandomUtil;
import com.example.wakeUp.global.config.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImp{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;
    private final MailService mailService;
    private final RedisService redisService;

    @Override
    public void signUp(CreateUserRequestDto request) {
        userFacade.validateSignUp(request.getEmail(), request.getName());
        userRepository.save(request.toEntity(passwordEncoder));
    }

    @Transactional
    public void issueCode(String email) {

        String code = RandomUtil.issue();
        redisService.setDataExpire(email, code, Duration.ofMinutes(3));

        mailService.sendMail(
                email,
                "ISSUE CODE",
                mailService.genTemplateEngine(
                        "issue-code",
                        Map.of("code", code)
                ),
                true
        );
    }

    @Transactional
    public boolean checkCode(String code, String email) {
        userFacade.checkCode(code, email);
        redisService.delete(email);
        return true;
    }
}
