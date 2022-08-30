package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.domain.repository.UserRepository;
import com.example.wakeUp.domain.user.presentation.dto.request.CodeRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userServiceImp;
    private final UserRepository userRepository;

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
    public boolean checkCode(
            @RequestBody CodeRequestDto request
    ) {
        return userServiceImp.checkCode(request.getCode(), request.getEmail());
    }

    @DeleteMapping
    public void deleteAll() {
        userRepository.deleteAll();
    }
}