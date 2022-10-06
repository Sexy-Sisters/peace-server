package com.example.wakeUp.domain.song.presentation;

import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.song.presentation.dto.response.SongResponseDto;
import com.example.wakeUp.domain.song.service.DailyRankingService;
import com.example.wakeUp.domain.song.service.SongService;
import com.example.wakeUp.global.crawler.SearchSong;
import com.example.wakeUp.global.crawler.dto.SearchSongDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Tag(name = "노래", description = "노래 관련 API 명세서입니다.")
@RestController
@RequestMapping("/api/song")
@RequiredArgsConstructor
@Slf4j
public class SongController {

    private final SongService songService;
    private final DailyRankingService dailyRankingService;
    private final SearchSong searchSong;

    @Operation(summary = "노래 신청")
    @PostMapping
    public void requestSong(@RequestBody CreateSongRequestDto dto) {
        log.info("<<<<<====== [POST]: /api/song =====>>>>>");
        songService.requestSong(dto);
    }

    @Operation(summary = "노래 아이디로 노래 삭제")
    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        log.info("<<<<<====== [DELETE]: /api/song =====>>>>>");
        songService.deleteSong(id);
    }

    @Operation(summary = "Daily 차트")
    @GetMapping
    public List<SongResponseDto> getSongChart() {
        log.info("<<<<<====== [GET]: /api/song =====>>>>>");
        return dailyRankingService.getRankingList();
    }

    @Operation(summary = "노래 검색")
    @GetMapping("/search")
    public List<SearchSongDto> searchSong(@PathParam("word")String word) {
        return searchSong.search(word);
    }
}

