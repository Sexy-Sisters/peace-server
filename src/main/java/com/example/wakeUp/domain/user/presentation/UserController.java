package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.presentation.dto.request.CodeRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userServiceImp;
    private final UserRepository userRepository;

    @PostMapping
    public void signUp(@RequestBody @Valid CreateUserRequestDto request) {
        log.info("<<<<<====== [POST]: /api/user =====>>>>>");
        userServiceImp.signUp(request);
    }

    @PostMapping("/issue-code")
    public void issueCode(@RequestParam String email) {
        log.info("<<<<<====== [GET]: /api/user =====>>>>>");
        userServiceImp.issueCode(email);
    }

    @DeleteMapping("/check-code")
    public boolean checkCode(@RequestBody CodeRequestDto request) {
        log.info("<<<<<====== [POST]: /api/user/check-code =====>>>>>");
        return userServiceImp.checkCode(request.getCode(), request.getEmail());
    }
}
