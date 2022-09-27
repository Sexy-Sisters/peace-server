package com.example.wakeUp.domain.song.service;

import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.song.presentation.dto.response.SongResponseDto;
import com.example.wakeUp.global.redis.RedisSortedSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyRankingService {

    private final RedisSortedSetService redisSortedSetService;
    private final SongFacade songFacade;

    private final String KEY = "DAILY-CHART";

    public void push(Song song) {
        redisSortedSetService.push(KEY, song.getIdentify(), song.getUps().size());
    }

    public void remove(Song song) {
        redisSortedSetService.remove(KEY, song.getIdentify());
    }

    public void removeAll() {
        redisSortedSetService.removeAll(KEY);
    }

    public List<SongResponseDto> getRankingList() {
        long size = redisSortedSetService.getSize(KEY);
        long limitSize = size < 10 ? size : 10;

        return redisSortedSetService.getRankingList(KEY, 0, 10).stream()
                .limit(limitSize)
                .map(songFacade::findSongByIdentify)
                .map(SongResponseDto::of)
                .collect(Collectors.toList());
    }
}
