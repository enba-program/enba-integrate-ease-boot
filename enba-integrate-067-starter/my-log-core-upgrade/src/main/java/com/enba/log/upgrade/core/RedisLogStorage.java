package com.enba.log.upgrade.core;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisLogStorage implements LogStorage {

  private final StringRedisTemplate stringRedisTemplate;

  public RedisLogStorage(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

  @Override
  public void store(String message) {
    // 实现将日志存储到Redis的逻辑
    log.info("使用redis存储日志:{}", message);
  }
}
