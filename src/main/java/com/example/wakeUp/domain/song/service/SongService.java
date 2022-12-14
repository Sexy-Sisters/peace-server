package com.example.wakeUp.domain.song.service;

import com.example.wakeUp.domain.chart.domain.Chart;
import com.example.wakeUp.domain.chart.domain.repository.ChartRepository;
import com.example.wakeUp.domain.chart.facade.ChartFacade;
import com.example.wakeUp.domain.chart.service.ChartService;
import com.example.wakeUp.domain.chart.service.MonthlyRankingService;
import com.example.wakeUp.domain.song.domain.Song;
import com.example.wakeUp.domain.song.domain.repository.SongRepository;
import com.example.wakeUp.domain.song.domain.repository.UpRepository;
import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.song.presentation.dto.request.CreateSongRequestDto;
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
    private final ChartRepository chartRepository;
    private final UpRepository upRepository;
    private final ChartFacade chartFacade;

    @Transactional
    public void requestSong(CreateSongRequestDto dto) {
        User user = userFacade.getCurrentUser();
        songFacade.validateRequestSong(dto, user);

        Song song = dto.toEntity(user);

        chartService.addChart(song);
        dailyRankingService.push(song);

        songRepository.save(song);
    }

    @Transactional
    public void deleteSong(Long id) {
        Song song = songFacade.findSongById(id);
        User user = userFacade.getCurrentUser();

        songFacade.validateDeleteSong(song, user);

        dailyRankingService.remove(song);

        String redisKey = song.getTitle()+"@"+song.getSinger();
        Chart chart = chartFacade.findChartByRedisKey(redisKey);

        if (chart.getPoint() == 0 || chart.getPoint() == song.getUps().size()) {
            monthlyRankingService.remove(redisKey);
            chartRepository.deleteByRedisKey(redisKey);
        }
        else {
            chart.decreasePoint(song.getUps().size());
            monthlyRankingService.push(chart);
        }

        upRepository.deleteBySong(song);
        songRepository.delete(song);
    }
}
