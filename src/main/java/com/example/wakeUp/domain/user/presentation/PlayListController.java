package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.AddPlayListRequestDto;
import com.example.wakeUp.domain.user.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/playlist")
@RequiredArgsConstructor
public class PlayListController {

    private final PlayListService playListService;

    @PostMapping
    public void addPlayList(@RequestBody @Valid AddPlayListRequestDto dto) {
        playListService.addPlayList(dto);
    }

    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable Long id) {
        playListService.deletePlayList(id);
    }
}
