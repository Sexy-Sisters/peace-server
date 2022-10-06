package com.example.wakeUp.domain.song.presentation;

import com.example.wakeUp.domain.song.service.UpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "노래 좋아요", description = "노래 좋아요 관련 API 명세서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/song/{id}/up")
@Slf4j
public class UpController {

    private final UpService upService;

    @Operation(summary = "노래 아이디로 좋아요 누르기")
    @PostMapping
    public int pushUp(@PathVariable Long id) {
        log.info("<<<<<====== [POST]: /api/song/{id}/up =====>>>>>");
        return upService.pushUp(id);
    }

    @Operation(summary = "노래 아이디로 좋아요 취소")
    @DeleteMapping
    public int deleteUp(@PathVariable Long id) {
        log.info("<<<<<====== [DELETE]: /api/song/{id}/up =====>>>>>");
        return upService.cancelUp(id);
    }

    @Operation(summary = "노래 아이디로 노래에 좋아요 눌렀는지 조회")
    @GetMapping
    public boolean isPush(@PathVariable Long id) {
        log.info("<<<<<====== [GET]: /api/song/{id}/up =====>>>>>");
        return upService.isPush(id);
    }
}
