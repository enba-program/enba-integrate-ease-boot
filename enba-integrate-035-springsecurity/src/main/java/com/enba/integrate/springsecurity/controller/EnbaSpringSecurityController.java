package com.enba.integrate.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-spring-security")
public class EnbaSpringSecurityController {

  @Autowired AuthenticationManager authenticationManager;

  /** 模拟业务接口，会被拦截 */
  @GetMapping("/test")
  public String test() {
    return "test...";
  }

  /** 登陆接口 放行 */
  @GetMapping("/login")
  public String login() {
    /// 1.封装Authentication对象
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken("username", "123");

    // 2.通过AuthenticationManager的authenticate方法来进行用户认证
    Authentication authenticated = authenticationManager.authenticate(authenticationToken);

    // 将认证令牌设置到安全上下文
    SecurityContextHolder.getContext().setAuthentication(authenticated);

    return "登陆成功...";
  }

  /** 退出接口放行 */
  @GetMapping("/logout")
  public String logout() {

    return "退出成功...";
  }
}
