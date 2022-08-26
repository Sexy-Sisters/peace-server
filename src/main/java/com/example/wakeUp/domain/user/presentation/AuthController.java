package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.LoginRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.TokenResponseDto;
import com.example.wakeUp.domain.user.facade.UserFacade;
import com.example.wakeUp.domain.user.service.AuthService;
import com.example.wakeUp.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public TokenResponseDto login(
            @RequestBody final LoginRequestDto request
    ) {
        return authService.login(request);
    }

    @DeleteMapping
    public void logout(HttpServletRequest request) {
        authService.logout(request);
    }

    @PutMapping
    public TokenResponseDto getNewAccessToken(
            @RequestHeader(value = "Refresh-Token") String refreshToken
    ) {
        return authService.getNewAccessToken(refreshToken);
    }

    @GetMapping
    public String getCurrentUser() {
        return userFacade.getCurrentUser().getName();
    }
}
