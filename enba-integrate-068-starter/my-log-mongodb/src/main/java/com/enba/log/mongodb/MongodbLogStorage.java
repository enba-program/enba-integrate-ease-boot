package com.enba.log.mongodb;

import com.enba.log.core.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongodbLogStorage implements LogStorage {

  @Autowired private MongoTemplate mongoTemplate;

  @Override
  public void storeLog(String logMessage) {
    LogDocument logDocument = new LogDocument(logMessage);
    mongoTemplate.insert(logDocument, "logs");
  }
}
