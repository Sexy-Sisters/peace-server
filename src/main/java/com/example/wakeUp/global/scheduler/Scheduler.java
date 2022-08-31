package com.example.wakeUp.global.scheduler;

import com.example.wakeUp.domain.song.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final RankingService rankingService;

    @Scheduled(cron = "0 0 23 * * *")
    public void scheduleChartClearTask() {
        Date now = new Date();
        System.out.println("BSSM CHART Cleared !!! :: " + now);
        rankingService.removeAll();
    }
}
