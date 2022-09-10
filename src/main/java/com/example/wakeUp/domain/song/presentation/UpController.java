package com.example.wakeUp.domain.song.presentation;

import com.example.wakeUp.domain.song.service.UpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/song/{id}/up")
@Slf4j
public class UpController {

    private final UpService upService;

    @PostMapping
    public int pushUp(@PathVariable Long id) {
        log.info("<<<<<====== [POST]: /api/song/{id}/up =====>>>>>");
        return upService.pushUp(id);
    }

    @DeleteMapping
    public int deleteUp(@PathVariable Long id) {
        log.info("<<<<<====== [DELETE]: /api/song/{id}/up =====>>>>>");
        return upService.cancelUp(id);
    }

    @GetMapping
    public boolean isPush(@PathVariable Long id) {
        log.info("<<<<<====== [GET]: /api/song/{id}/up =====>>>>>");
        return upService.isPush(id);
    }
}
