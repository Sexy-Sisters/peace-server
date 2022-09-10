package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.LoginRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.TokenResponseDto;
import com.example.wakeUp.domain.user.facade.UserFacade;
import com.example.wakeUp.domain.user.service.AuthService;
import com.example.wakeUp.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserFacade userFacade;

    @PostMapping
    public TokenResponseDto login(@RequestBody final LoginRequestDto request) {
        log.info("<<<<<====== [POST]: /api/auth =====>>>>>");
        return authService.login(request);
    }

    @DeleteMapping
    public void logout(HttpServletRequest request) {
        log.info("<<<<<====== [DELETE]: /api/auth =====>>>>>");
        authService.logout(request);
    }

    @PutMapping
    public TokenResponseDto getNewAccessToken(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        log.info("<<<<<====== [PUT]: /api/auth =====>>>>>");
        return authService.getNewAccessToken(refreshToken);
    }

    @GetMapping
    public String getCurrentUser() {
        log.info("<<<<<====== [GET]: /api/auth =====>>>>>");
        return userFacade.getCurrentUser().getName();
    }
}
