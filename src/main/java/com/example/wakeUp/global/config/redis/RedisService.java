package com.example.wakeUp.global.config.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, Object> blackListTemplate;

    public void setData(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void setDataExpire(String key, String value, Duration duration) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value, duration);
    }

    public String getData(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void setBlackList(String key, Object o, long time) {
        blackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        blackListTemplate.opsForValue().set(key, o, time, TimeUnit.MILLISECONDS);
    }

    public boolean hasBlackList(String key) {
        return Boolean.TRUE.equals(blackListTemplate.hasKey(key));
    }
}
