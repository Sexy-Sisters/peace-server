package com.example.wakeUp.domain.chart.domain.repository;

import com.example.wakeUp.domain.chart.domain.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository<Chart, Long> {

    boolean existsByTitleAndSinger(String title, String singer);
}
