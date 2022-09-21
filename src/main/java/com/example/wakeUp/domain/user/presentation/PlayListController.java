package com.example.wakeUp.domain.user.presentation;

import com.example.wakeUp.domain.user.presentation.dto.request.AddPlayListRequestDto;
import com.example.wakeUp.domain.user.presentation.dto.response.PlayListResponseDto;
import com.example.wakeUp.domain.user.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/playlist")
@RequiredArgsConstructor
public class PlayListController {

    private final PlayListService playListService;

    @PostMapping
    public void addPlayList(@RequestBody @Valid AddPlayListRequestDto dto) {
        playListService.addPlayList(dto);
    }

    @GetMapping("/{userId}")
    public List<PlayListResponseDto> findPlayList(@PathVariable Long userId) {
        return playListService.findPlayList(userId);
    }

    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable Long id) {
        playListService.deletePlayList(id);
    }

}
