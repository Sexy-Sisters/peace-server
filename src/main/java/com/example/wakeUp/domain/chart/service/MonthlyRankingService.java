package com.example.wakeUp.domain.chart.service;

import com.example.wakeUp.domain.chart.domain.Chart;
import com.example.wakeUp.domain.chart.dto.ChartResponseDto;
import com.example.wakeUp.domain.chart.facade.ChartFacade;
import com.example.wakeUp.global.redis.RedisSortedSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonthlyRankingService {

    private final RedisSortedSetService redisSortedSetService;
    private final ChartFacade chartFacade;

    private final String KEY = "MONTHLY-CHART";

    public void push(Chart chart) {
        redisSortedSetService.push(KEY, chart.getRedisKey(), chart.getPoint());
    }

    public void remove(String redisKey) {
        redisSortedSetService.remove(KEY, redisKey);
    }

    public void removeAll() {
        redisSortedSetService.removeAll(KEY);
    }

    @Transactional(readOnly = true)
    public List<ChartResponseDto> getMonthlyRankingList() {
        long setSize = redisSortedSetService.getSize(KEY);
        long limitSize = setSize < 20 ? setSize : 20;

        return redisSortedSetService.getRankingList(KEY, 0, 20).stream()
                .limit(limitSize)
                .map(chartFacade::findChartByRedisKey)
                .map(ChartResponseDto::of)
                .collect(Collectors.toList());
    }
}
