package com.enba.autoconfigure;

import com.enba.log.upgrade.core.ElasticsearchLogStorage;
import com.enba.log.upgrade.core.LogService;
import com.enba.log.upgrade.core.LogStorage;
import com.enba.log.upgrade.core.MongoDbLogStorage;
import com.enba.log.upgrade.core.RedisLogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@ConditionalOnClass(LogService.class)
public class LogUpgradeAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public LogService logService(LogStorage logStorage) {
    return new LogService(logStorage);
  }

  //  @Bean
  //  @ConditionalOnProperty(name = "log.storage.type", havingValue = "mysql")
  //  public LogStorage mysqlLogStorage(JdbcTemplate jdbcTemplate) {
  //    return new MysqlLogStorage(jdbcTemplate);
  //  }

  @Bean
  @ConditionalOnProperty(name = "log.storage.type", havingValue = "redis")
  public LogStorage redisLogStorage(
      @Autowired(required = false) StringRedisTemplate stringRedisTemplate) {
    return new RedisLogStorage(stringRedisTemplate);
  }

  @Bean
  @ConditionalOnProperty(name = "log.storage.type", havingValue = "mongodb")
  public LogStorage mongoDbLogStorage(@Autowired(required = false) MongoTemplate mongoTemplate) {
    return new MongoDbLogStorage(mongoTemplate);
  }

  @Bean
  @ConditionalOnProperty(name = "log.storage.type", havingValue = "es")
  public LogStorage elasticsearchLogStorage(
      @Autowired(required = false) ElasticsearchOperations elasticsearchOperations) {
    return new ElasticsearchLogStorage(elasticsearchOperations);
  }
}
