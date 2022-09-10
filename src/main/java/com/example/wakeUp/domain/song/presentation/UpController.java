package com.example.wakeUp.domain.song.presentation;

import com.example.wakeUp.domain.song.service.UpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/song/{id}/up")
public class UpController {

    private final UpService upService;

    @PostMapping
    public void pushUp(
            @PathVariable Long id
    ) {
        upService.pushUp(id);
    }

    @DeleteMapping
    public void deleteUp(
            @PathVariable Long id
    ) {
        upService.cancelUp(id);
    }

    @GetMapping
    public boolean isPush(
            @PathVariable Long id
    ) {
        return upService.isPush(id);
    }
}
