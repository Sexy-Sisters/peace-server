package com.example.wakeUp.domain.song.service;

import com.example.wakeUp.domain.chart.service.ChartService;
import com.example.wakeUp.domain.chart.service.MonthlyRankingService;
import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.repository.SongRepository;
import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.user.domain.User;
import com.example.wakeUp.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {

    private final SongRepository songRepository;
    private final UserFacade userFacade;
    private final SongFacade songFacade;
    private final DailyRankingService dailyRankingService;
    private final ChartService chartService;
    private final MonthlyRankingService monthlyRankingService;

    @Transactional
    public void requestSong(CreateSongRequestDto dto) {
        User user = userFacade.getCurrentUser();
        songFacade.validateRequestSong(dto, user);

        Song song = dto.toEntity(user);
        chartService.addChart(song);

        String redisKey = song.getTitle()+"@"+song.getSinger();
        monthlyRankingService.push(redisKey, 0);

        dailyRankingService.push(song.getIdentify(), song.getUps().size());
        songRepository.save(song);
    }

    @Transactional
    public void deleteSong(Long id) {
        Song song = songFacade.findSongById(id);

        dailyRankingService.remove(song.getIdentify());

        String redisKey = song.getTitle()+"@"+song.getSinger();
        monthlyRankingService.remove(redisKey);

        songRepository.delete(song);
    }
}
