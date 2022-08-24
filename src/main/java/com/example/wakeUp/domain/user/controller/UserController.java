package com.example.wakeUp.domain.user.controller;

import com.example.wakeUp.domain.user.controller.dto.request.CodeRequestDto;
import com.example.wakeUp.domain.user.controller.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userServiceImp;

    @PostMapping
    public void signUp(
            @RequestBody CreateUserRequestDto request
    ) {
        userServiceImp.signUp(request);
    }

    @GetMapping("/issue-code")
    public void issueCode(
            @RequestParam String email
    ) {
       userServiceImp.issueCode(email);
    }

    @PostMapping("/check-code")
    public String checkCode(
            @RequestBody CodeRequestDto request
    ) {
        return userServiceImp.checkCode(request.getCode(), request.getEmail());
    }
}
