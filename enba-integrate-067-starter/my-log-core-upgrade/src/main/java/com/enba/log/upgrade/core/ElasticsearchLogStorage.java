package com.enba.log.upgrade.core;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

public class ElasticsearchLogStorage implements LogStorage {

  private final ElasticsearchOperations elasticsearchOperations;

  public ElasticsearchLogStorage(ElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  @Override
  public void store(String message) {
    // 实现将日志存储到Elasticsearch的逻辑

    log.info("使用es存储日志:{}", message);
  }
}
