package com.example.wakeUp.domain.song.presentation;

import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.song.presentation.dto.response.SongResponseDto;
import com.example.wakeUp.domain.song.service.DailyRankingService;
import com.example.wakeUp.domain.song.service.SongService;
import com.example.wakeUp.global.crawler.SearchSong;
import com.example.wakeUp.global.crawler.dto.SearchSongDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/song")
@RequiredArgsConstructor
@Slf4j
public class SongController {

    private final SongService songService;
    private final DailyRankingService dailyRankingService;
    private final SearchSong searchSong;

    @PostMapping
    public void requestSong(@RequestBody CreateSongRequestDto dto) {
        log.info("<<<<<====== [POST]: /api/song =====>>>>>");
        songService.requestSong(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        log.info("<<<<<====== [DELETE]: /api/song =====>>>>>");
        songService.deleteSong(id);
    }

    @GetMapping
    public List<SongResponseDto> getSongChart() {
        log.info("<<<<<====== [GET]: /api/song =====>>>>>");
        return dailyRankingService.getRankingList();
    }

    @GetMapping("/search")
    public List<SearchSongDto> searchSong(@PathParam("word")String word) {
        return searchSong.search(word);
    }
}

