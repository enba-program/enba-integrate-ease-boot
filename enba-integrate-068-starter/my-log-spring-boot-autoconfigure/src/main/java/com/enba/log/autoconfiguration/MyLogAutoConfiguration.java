package com.enba.log.autoconfiguration;

import com.enba.log.es.ElasticsearchLogStorage;
import com.enba.log.core.LogStorage;
import com.enba.log.mongodb.MongodbLogStorage;
import com.enba.log.mysql.MysqlLogStorage;
import com.enba.log.redis.RedisLogStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyLogAutoConfiguration {

  public static final Logger log = LoggerFactory.getLogger(MyLogAutoConfiguration.class);

  @Bean
  @ConditionalOnClass(name = "com.mysql.cj.jdbc.Driver")
  @ConditionalOnProperty(prefix = "myapp.log.storage", name = "type", havingValue = "mysql")
  @ConditionalOnMissingBean
  public LogStorage mysqlLogStorage() {
    log.info("{{{{{{日志存储使用mysqlLogStorage}}}}}}");
    return new MysqlLogStorage();
  }

  @Bean
  @ConditionalOnClass(name = "org.springframework.data.redis.connection.RedisConnectionFactory")
  @ConditionalOnProperty(prefix = "myapp.log.storage", name = "type", havingValue = "redis")
  @ConditionalOnMissingBean
  public LogStorage redisLogStorage() {
    log.info("{{{{{{日志存储使用redisLogStorage}}}}}}");
    return new RedisLogStorage();
  }

  @Bean
  @ConditionalOnClass(name = "com.mongodb.client.MongoClient")
  @ConditionalOnProperty(prefix = "myapp.log.storage", name = "type", havingValue = "mongodb")
  @ConditionalOnMissingBean
  public LogStorage mongodbLogStorage() {
    log.info("{{{{{{日志存储使用mongodbLogStorage}}}}}}");
    return new MongodbLogStorage();
  }

  @Bean
  @ConditionalOnClass(name = "org.elasticsearch.client.RestHighLevelClient")
  @ConditionalOnProperty(prefix = "myapp.log.storage", name = "type", havingValue = "elasticsearch")
  @ConditionalOnMissingBean
  public LogStorage elasticsearchLogStorage() {
    log.info("{{{{{{日志存储使用elasticsearchLogStorage}}}}}}");
    return new ElasticsearchLogStorage();
  }

}