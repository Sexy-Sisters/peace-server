package com.example.wakeUp.domain.user.domain.controller;

import com.example.wakeUp.domain.user.domain.controller.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void issueCode() {
       userServiceImp.issueCode();
    }
}
