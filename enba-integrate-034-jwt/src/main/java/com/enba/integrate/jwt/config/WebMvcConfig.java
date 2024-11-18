package com.enba.integrate.jwt.config;

import com.enba.integrate.jwt.interceptor.EnbaAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 配置拦截器路径 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 向拦截器注册表中添加自定义拦截器
    registry
        // 创建一个新的EnbaAuthInterceptor实例并添加到注册表
        .addInterceptor(new EnbaAuthInterceptor())
        // 拦截的路径
        .addPathPatterns("/**")
        // 开放的路径 比如：登录接口，注册接口等不需要登录就能访问的接口地址
        .excludePathPatterns("/enba-jwt/login/**", "/enba-jwt/check-token");
  }
}
