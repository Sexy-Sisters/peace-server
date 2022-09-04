package com.example.wakeUp.domain.chart.service;

import com.example.wakeUp.domain.chart.domain.Chart;
import com.example.wakeUp.domain.chart.domain.repository.ChartRepository;
import com.example.wakeUp.domain.song.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final ChartRepository chartRepository;

    @Transactional
    public void addChart(Song song) {
        if (!chartRepository.existsByTitleAndSinger(song.getTitle(), song.getSinger())) {
            chartRepository.save(Chart.createChart(song));
        }
    }
}
