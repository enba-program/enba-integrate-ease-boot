package com.enba.log.autoconfigure;

import com.enba.log.core.LogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LogService.class)
public class MyLogAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public LogService logService() {
    return new LogService();
  }
}