package com.enba.integrate.resttemplate.config;

import com.enba.integrate.resttemplate.handler.CustomResponseErrorHandler;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  /**
   * 通过@Bean注解创建一个RestTemplate的实例 该实例被Spring框架管理，并命名为"defaultRestTemplate"
   * RestTemplate用于执行HTTP请求，发送HTTP响应，是Spring提供的简化HTTP交互的模板类
   *
   * @return 返回一个新的RestTemplate实例，用于后续的HTTP操作
   */
  @Bean("defaultRestTemplate")
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /**
   * 配置高级RestTemplate实例 通过使用RestTemplateBuilder，你可以更加方便地设置RestTemplate的各种选项
   * 此方法创建了一个RestTemplate实例，用于处理HTTP请求，并应用了自定义的错误处理策略
   *
   * @param restTemplateBuilder RestTemplate的构建器，用于简化RestTemplate的配置
   * @param customResponseErrorHandler 自定义的响应错误处理器，用于处理HTTP响应中可能出现的错误
   * @return 返回配置好的RestTemplate实例，供应用程序使用
   */
  @Bean(name = "advancedRestTemplate")
  public RestTemplate restTemplate(
      RestTemplateBuilder restTemplateBuilder,
      CustomResponseErrorHandler customResponseErrorHandler) {

    return restTemplateBuilder
        .setConnectTimeout(Duration.ofDays(30 * 1000)) // 设置连接超时时间为30天，确保有足够的时间来建立连接
        .setReadTimeout(Duration.ofDays(60 * 1000)) // 设置读取超时时间为60天，确保有足够的时间来读取响应
        .errorHandler(customResponseErrorHandler) // 设置自定义的错误处理器，以处理HTTP响应中的错误
        .build(); // 构建并返回RestTemplate实例
  }
}
