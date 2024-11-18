package com.enba.log.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "myapp.log.storage")
public class MyLogProperties {

  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
