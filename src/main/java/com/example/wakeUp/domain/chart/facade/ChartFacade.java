package com.example.wakeUp.domain.chart.facade;

import com.example.wakeUp.domain.chart.domain.Chart;
import com.example.wakeUp.domain.chart.domain.repository.ChartRepository;
import com.example.wakeUp.domain.chart.exception.ChartNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChartFacade {

    private final ChartRepository chartRepository;

    public Chart findChartByTitleAndSinger(String title, String singer) {
        return chartRepository.findByTitleAndSinger(title, singer)
                .orElseThrow(() -> ChartNotFoundException.EXCEPTION);
    }
}
