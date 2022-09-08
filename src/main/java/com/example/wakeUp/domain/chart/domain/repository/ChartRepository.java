package com.example.wakeUp.domain.chart.domain.repository;

import com.example.wakeUp.domain.chart.domain.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChartRepository extends JpaRepository<Chart, Long> {

    boolean existsByTitleAndSinger(String title, String singer);

    Optional<Chart> findByTitleAndSinger(String title, String singer);

    Optional<Chart> findByRedisKey(String redisKey);
}
