package com.example.wakeUp.domain.chart.presentation;

import com.example.wakeUp.domain.chart.dto.ChartResponseDto;
import com.example.wakeUp.domain.chart.service.MonthlyRankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chart")
@RequiredArgsConstructor
@Slf4j
public class ChartController {

    private final MonthlyRankingService monthlyRankingService;

    @GetMapping
    public List<ChartResponseDto> getMonthlyRanking() {
        log.info("<<<<<====== [GET]: /api/chart =====>>>>>");
        return monthlyRankingService.getMonthlyRankingList();
    }
}
