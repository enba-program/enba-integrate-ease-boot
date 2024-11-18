package com.enba.integrate.i18n.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class I18nWebAppConfigurer implements WebMvcConfigurer {

  @Bean
  public I18nHandlerInterceptor initI18nHandler() {
    return new I18nHandlerInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 在拦截器注册表中添加国际化拦截器，处理国际化相关请求
    registry
        .addInterceptor(initI18nHandler())
        // 设置拦截器对所有路径生效
        .addPathPatterns("/**")
        // 设置拦截器的执行顺序，越小越先执行
        .order(-1);
  }
}
