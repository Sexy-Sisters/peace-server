package com.example.wakeUp.domain.chart.presentation;

import com.example.wakeUp.domain.chart.dto.ChartResponseDto;
import com.example.wakeUp.domain.chart.service.MonthlyRankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "월간 차트", description = "월간 차트 관련 API 명세서입니다.")
@RestController
@RequestMapping("/api/chart")
@RequiredArgsConstructor
@Slf4j
public class ChartController {

    private final MonthlyRankingService monthlyRankingService;

    @Operation(summary = "월간 차트 조회")
    @GetMapping
    public List<ChartResponseDto> getMonthlyRanking() {
        log.info("<<<<<====== [GET]: /api/chart =====>>>>>");
        return monthlyRankingService.getMonthlyRankingList();
    }
}
