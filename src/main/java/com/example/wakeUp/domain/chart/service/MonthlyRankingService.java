package com.example.wakeUp.domain.chart.service;

import com.example.wakeUp.domain.chart.dto.ChartResponseDto;
import com.example.wakeUp.domain.chart.facade.ChartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonthlyRankingService {

    private final RedisTemplate redisTemplate;
    private ZSetOperations<String, String> zSetOps;
    private final ChartFacade chartFacade;

    private final String MONTHLY_CHART_KEY = "WEEKLY-CHART";

    @PostConstruct
    public void init() {
        zSetOps = redisTemplate.opsForZSet();
    }

    public void push(String redisKey, int point) {
        zSetOps.add(MONTHLY_CHART_KEY, redisKey, point);
    }

    public void remove(String redisKey) {
        if (zSetOps.score(MONTHLY_CHART_KEY, redisKey) == 0) {
            zSetOps.remove(MONTHLY_CHART_KEY, redisKey);
        }
    }

    public void removeAll() {
        while(zSetOps.size(MONTHLY_CHART_KEY) != 0) {
            zSetOps.popMax(MONTHLY_CHART_KEY);
        }
    }

    @Transactional(readOnly = true)
    public List<ChartResponseDto> getMonthlyRankingList() {
        Set<String> ranking = zSetOps.reverseRange(MONTHLY_CHART_KEY, 0, 20);

        long setSize = zSetOps.size(MONTHLY_CHART_KEY);
        long limitSize = setSize < 20 ? setSize : 20;

        return ranking.stream()
                .limit(limitSize)
                .map(chartFacade::findChartByRedisKey)
                .map(ChartResponseDto::of)
                .collect(Collectors.toList());
    }
}
