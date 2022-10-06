package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.CodeRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.MyPageResponseDto;
import com.example.wakeUp.domain.user.presentation.dto.response.UserResponseDto;
import com.example.wakeUp.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;


@Tag(name="유저", description = "유저 관련 API입니다.")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userServiceImp;

    @Operation(summary = "회원가입")
    @PostMapping
    public void signUp(@RequestBody @Valid CreateUserRequestDto request) {
        log.info("<<<<<====== [POST]: /api/user =====>>>>>");
        userServiceImp.signUp(request);
    }

    @Operation(summary = "이메일 인증코드 발송")
    @PostMapping("/issue-code")
    public void issueCode(@RequestParam String email) {
        log.info("<<<<<====== [GET]: /api/user =====>>>>>");
        userServiceImp.issueCode(email);
    }

    @Operation(summary = "인증코드 인증")
    @DeleteMapping("/check-code")
    public boolean checkCode(@RequestBody CodeRequestDto request) {
        log.info("<<<<<====== [POST]: /api/user/check-code =====>>>>>");
        return userServiceImp.checkCode(request.getCode(), request.getEmail());
    }

    @Operation(summary = "마이페이지 조회")
    @GetMapping("/profile")
    public MyPageResponseDto getMyPage() {
        log.info("<<<<<======[GET]: /api/user/profile =====>>>>>");
        return userServiceImp.findMyPage();
    }

    @Operation(summary = "프로필 이미지 수정")
    @PutMapping("/profile/img")
    public String updateProfileImg(@RequestParam(value = "image" )MultipartFile multipartFile) throws IOException {
        log.info("<<<<<======[PUT]: /api/user/profile/img =====>>>>>");
        return userServiceImp.updateProfile(multipartFile);
    }

    @Operation(summary = "유저 전부 조회 (순서 랜덤)")
    @GetMapping
    public Set<UserResponseDto> findUsers() {
        log.info("<<<<<======[GET]: /api/user =====>>>>>");
        return userServiceImp.findUsers();
    }
}
