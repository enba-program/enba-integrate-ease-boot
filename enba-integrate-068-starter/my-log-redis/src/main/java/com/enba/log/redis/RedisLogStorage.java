package com.enba.log.redis;

import com.enba.log.core.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLogStorage implements LogStorage {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void storeLog(String logMessage) {
        redisTemplate.opsForList().rightPush("logs", logMessage);
    }
}