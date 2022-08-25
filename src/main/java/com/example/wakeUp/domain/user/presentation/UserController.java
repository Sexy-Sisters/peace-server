package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.CodeRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userServiceImp;

    @PostMapping
    public void signUp(
            @RequestBody @Valid CreateUserRequestDto request
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
