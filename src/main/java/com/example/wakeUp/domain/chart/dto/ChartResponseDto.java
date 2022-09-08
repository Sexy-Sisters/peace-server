package com.example.wakeUp.domain.chart.dto;

import com.example.wakeUp.domain.chart.domain.Chart;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChartResponseDto {

    private Long id;

    private String imgUrl;

    private String title;

    private String singer;

    private int point;

    public static ChartResponseDto of(Chart chart) {
        return ChartResponseDto.builder()
                .id(chart.getId())
                .imgUrl(chart.getImgUrl())
                .title(chart.getTitle())
                .singer(chart.getSinger())
                .point(chart.getPoint())
                .build();
    }
}
