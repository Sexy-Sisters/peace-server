package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.facade.UserFacade;
import com.example.wakeUp.domain.user.presentation.dto.request.LoginRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.TokenResponseDto;
import com.example.wakeUp.domain.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "인증", description = "인증 관련 API 명세서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserFacade userFacade;

    @Operation(summary = "로그인")
    @PostMapping
    public TokenResponseDto login(@RequestBody final LoginRequestDto request) {
        log.info("<<<<<====== [POST]: /api/auth =====>>>>>");
        return authService.login(request);
    }

    @Operation(summary = "로그아웃")
    @DeleteMapping
    public void logout(HttpServletRequest request) {
        log.info("<<<<<====== [DELETE]: /api/auth =====>>>>>");
        authService.logout(request);
    }

    @Operation(summary = "인증 토큰 재발급")
    @PutMapping
    public TokenResponseDto getNewAccessToken(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        log.info("<<<<<====== [PUT]: /api/auth =====>>>>>");
        return authService.getNewAccessToken(refreshToken);
    }

    @Operation(summary = "유저 이름 반환")
    @GetMapping
    public String getCurrentUser() {
        log.info("<<<<<====== [GET]: /api/auth =====>>>>>");
        return userFacade.getCurrentUser().getName();
    }
}
