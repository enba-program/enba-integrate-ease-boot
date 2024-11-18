// LogDocument 类
package com.enba.log.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "logs")
public class LogDocument {
  @Id private String id;
  private String message;
  private long timestamp;

  public LogDocument(String message) {
    this.message = message;
    this.timestamp = System.currentTimeMillis();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  // Getters and Setters
}
