package com.enba.log.upgrade.core;

import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDbLogStorage implements LogStorage {

  private final MongoTemplate mongoTemplate;

  public MongoDbLogStorage(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void store(String message) {
    // 实现将日志存储到MongoDB的逻辑
    log.info("使用mongodb存储日志:{}", message);
  }
}
