package com.enba.integrate.redisson.config;

import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonAutoConfiguration {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Value("${enba.redisson.config}")
  private String redissonConfig;

  @Bean(destroyMethod = "shutdown")
  public RedissonClient redissonClient() throws IOException {
    log.info(">>>>>>>>>>RedissonClient<<<<<<<<<<");
    Config config = Config.fromYAML(redissonConfig);
    config.setCodec(new JsonJacksonCodec());
    return Redisson.create(config);
  }
}
