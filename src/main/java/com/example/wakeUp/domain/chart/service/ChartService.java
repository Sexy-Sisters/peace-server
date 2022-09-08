package com.example.wakeUp.domain.chart.service;

import com.example.wakeUp.domain.chart.domain.Chart;
import com.example.wakeUp.domain.chart.domain.repository.ChartRepository;
import com.example.wakeUp.domain.chart.facade.ChartFacade;
import com.example.wakeUp.domain.song.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final ChartRepository chartRepository;
    private final ChartFacade chartFacade;
    private final MonthlyRankingService monthlyRankingService;

    @Transactional
    public void addChart(Song song) {
        if (!chartRepository.existsByTitleAndSinger(song.getTitle(), song.getSinger())) {
            Chart chart = chartRepository.save(Chart.createChart(song));

            monthlyRankingService.push(chart.getRedisKey(), chart.getPoint());
        }
    }

    @Transactional
    public void increasePoint(String title, String singer) {
        Chart chart = chartFacade.findChartByTitleAndSinger(title, singer);
        chart.increasePoint();

        monthlyRankingService.push(chart.getRedisKey(), chart.getPoint());
    }

    @Transactional
    public void decreasePoint(String title, String singer) {
        Chart chart = chartFacade.findChartByTitleAndSinger(title, singer);
        chart.decreasePoint();

        monthlyRankingService.push(chart.getRedisKey(), chart.getPoint());
    }
}
