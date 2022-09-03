package com.example.wakeUp.domain.song.presentation;

import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.song.presentation.dto.response.SongResponseDto;
import com.example.wakeUp.domain.song.service.RankingService;
import com.example.wakeUp.domain.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final RankingService rankingService;

    @PostMapping
    public void requestSong(
            @RequestBody CreateSongRequestDto dto

    ) {
        songService.requestSong(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(
            @PathVariable Long id
    ) {
        songService.deleteSong(id);
    }

    @GetMapping
    public List<SongResponseDto> getSongChart() {
        return rankingService.getRankingList();
    }
}
