package com.example.wakeUp.global.scheduler;

import com.example.wakeUp.domain.chart.service.MonthlyRankingService;
import com.example.wakeUp.domain.song.service.DailyRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final DailyRankingService dailyRankingService;
    private final MonthlyRankingService monthlyRankingService;

    @Scheduled(cron = "0 0 23 * * *")
    public void scheduleChartClearTask() {
        Date now = new Date();
        System.out.println("Daily Chart Cleared !!! :: " + now);
        dailyRankingService.removeAll();
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void scheduleMonthlyChartClear() {
        Date now = new Date();
        System.out.println("Monthly Chart Cleared !!! :: " + now);
        monthlyRankingService.removeAll();
    }
}
