package com.example.wakeUp.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisSortedSetService {

    private final RedisTemplate redisTemplate;

    private ZSetOperations<String, String> zSetOps;

    @PostConstruct
    public void init() {
        zSetOps = redisTemplate.opsForZSet();
    }

    public void push(String key, String value, int score) {
        zSetOps.add(key, value, score);
    }

    public void remove(String key, String value) {
        zSetOps.remove(key, value);
    }

    public void removeAll(String key) {
        while(zSetOps.size(key) != 0) {
            zSetOps.popMax(key);
        }
    }

    public Set<String> getRankingList(String key, long start, long end) {
        return zSetOps.reverseRange(key, start, end);
    }

    public long getSize(String key) {
        return zSetOps.size(key);
    }
}
