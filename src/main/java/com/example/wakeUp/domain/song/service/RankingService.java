package com.example.wakeUp.domain.song.service;

import com.example.wakeUp.domain.song.facade.SongFacade;
import com.example.wakeUp.domain.song.presentation.dto.response.SongResponseDto;
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
public class RankingService {

    private final RedisTemplate redisTemplate;
    private ZSetOperations<String, String> zSetOps;
    private final SongFacade songFacade;

    private final String KEY = "BSSM_CHART";

    @PostConstruct
    public void init() {
        zSetOps = redisTemplate.opsForZSet();
    }

    public void push(String identify, int ups) {
        zSetOps.add(KEY, identify, ups);
    }

    public void remove(String id) {
        zSetOps.remove(KEY, id);
    }

    public void removeAll() {
        while(zSetOps.size(KEY) != 0) {
            System.out.println(zSetOps.size(KEY));
            zSetOps.popMax(KEY);
        }
    }

    @Transactional(readOnly = true)
    public List<SongResponseDto> getRankingList() {
        Set<String> ranking = zSetOps.reverseRange(KEY, 0, 9);

        long setSize = zSetOps.size(KEY);
        long limitSize = setSize <= 9 ? setSize : 9;

        return ranking.stream()
                .limit(limitSize)
                .map(songFacade::findSongByIdentify)
                .map(SongResponseDto::of)
                .collect(Collectors.toList());
    }
}
