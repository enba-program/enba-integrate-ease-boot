package com.enba.integrate.springsecurity.config;

import com.enba.integrate.springsecurity.filter.JwtAuthenticationTokenFilter;
import com.enba.integrate.springsecurity.handler.EnbaAccessDeniedHandler;
import com.enba.integrate.springsecurity.handler.EnbaAuthenticationEntryPoint;
import com.enba.integrate.springsecurity.handler.EnbaAuthenticationFailureHandler;
import com.enba.integrate.springsecurity.handler.EnbaAuthenticationSuccessHandler;
import com.enba.integrate.springsecurity.handler.EnbaLogoutSuccessHandler;
import com.enba.integrate.springsecurity.provider.EnbaAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {

  @Autowired EnbaAuthenticationProvider provider;

  @Autowired EnbaAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired EnbaAuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired EnbaAuthenticationFailureHandler authenticationFailureHandler;

  @Autowired EnbaLogoutSuccessHandler logoutSuccessHandler;

  @Autowired EnbaAccessDeniedHandler accessDeniedHandler;

  @Autowired AuthenticationConfiguration authenticationConfiguration;

  @Autowired JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

  /**
   * 配置认证管理器
   *
   * <p>此方法用于向AuthenticationManagerBuilder注册一个自定义的AuthenticationProvider 通过这种方式，我们可以使用自定义的认证逻辑和实现
   *
   * @param auth AuthenticationManagerBuilder实例，用于配置认证管理器
   * @throws Exception 如果配置过程中发生错误，可能会抛出异常
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(provider);
  }

  /**
   * 配置Spring Security
   *
   * @param http HttpSecurity对象，用于配置与HTTP请求相关的安全约束
   * @throws Exception 配置过程中可能抛出的异常
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // 禁用CSRF保护，因为可能不适用或已在其他层面上进行了处理
    http.csrf()
        .disable()
        // 配置基本认证，并指定自定义的认证入口点
        .httpBasic()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint)
        // 继续配置请求授权规则
        .and()
        .authorizeRequests()
        .antMatchers("/enba-spring-security/login")
        .permitAll()
        // 所有请求都需要经过认证
        .anyRequest()
        .authenticated()
        // 配置表单登录，并指定自定义的成功和失败处理程序
        .and()
        .formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler)
        // 登录功能对所有人开放
        .permitAll()
        // 配置注销功能，并指定自定义的注销成功处理程序
        .and()
        .logout()
        .logoutSuccessHandler(logoutSuccessHandler)
        // 注销功能对所有人开放
        .permitAll();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // 设置退出接口
    http.logout().logoutUrl("/enba-spring-security/logout");

    // 配置异常处理，指定自定义的访问拒绝处理程序
    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    // 添加JWT认证过滤器
    http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
