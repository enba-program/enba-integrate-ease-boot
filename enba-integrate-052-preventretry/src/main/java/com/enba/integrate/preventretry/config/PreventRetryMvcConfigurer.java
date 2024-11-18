package com.enba.integrate.preventretry.config;

import com.enba.integrate.preventretry.props.PreventRetryProperties;
import com.enba.integrate.preventretry.service.CacheTemplate;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PreventRetryMvcConfigurer implements WebMvcConfigurer {

  @Resource private PreventRetryProperties properties;

  @Resource private CacheTemplate cacheTemplate;

  @Bean
  public PreventRetryHandlerInterceptor preventRetry() {
    return new PreventRetryHandlerInterceptor(properties, cacheTemplate);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(preventRetry()).addPathPatterns("/**");
  }
}
