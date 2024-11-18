package com.enba.integrate.preventretry.config;

import com.enba.integrate.preventretry.props.PreventRetryProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis", matchIfMissing = true)
public class PreventRetryFilterAutoConfiguration {

  /**
   * 配置 PreventRetryProperties 类的 Bean 该 Bean 被命名为 "preventRetryProperties"，以便在 Spring 容器中进行依赖注入
   *
   * @return 返回一个初始化的 PreventRetryProperties 实例，包含防止重试的相关配置属性
   */
  @Bean("preventRetryProperties")
  @ConfigurationProperties(prefix = "enba.request.prevent-retry")
  public PreventRetryProperties preventRetryProperties() {
    return new PreventRetryProperties();
  }
}
