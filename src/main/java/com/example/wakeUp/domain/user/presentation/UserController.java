package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.CodeRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.MyPageResponseDto;
import com.example.wakeUp.domain.user.presentation.dto.response.UserResponseDto;
import com.example.wakeUp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userServiceImp;

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

    @GetMapping("/profile")
    public MyPageResponseDto getMyPage() {
        log.info("<<<<<======[GET]: /api/user/profile =====>>>>>");
        return userServiceImp.findMyPage();
    }

    @PutMapping("/profile/img")
    public String updateProfileImg(@RequestParam(value = "image" )MultipartFile multipartFile) throws IOException {
        log.info("<<<<<======[PUT]: /api/user/profile/img =====>>>>>");
        return userServiceImp.updateProfile(multipartFile);
    }

    @GetMapping
    public Set<UserResponseDto> findUsers() {
        log.info("<<<<<======[GET]: /api/user =====>>>>>");
        return userServiceImp.findUsers();
    }
}
