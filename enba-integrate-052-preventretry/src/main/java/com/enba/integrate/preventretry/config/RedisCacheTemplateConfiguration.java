package com.enba.integrate.preventretry.config;

import com.enba.integrate.preventretry.service.CacheTemplate;
import com.enba.integrate.preventretry.service.DefaultRedisCacheTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisCacheTemplateConfiguration {

  @Bean
  public CacheTemplate cacheTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    template.setKeySerializer(RedisSerializer.string());
    template.setHashKeySerializer(RedisSerializer.string());
    template.afterPropertiesSet();
    return new DefaultRedisCacheTemplate(template);
  }
}
