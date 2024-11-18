package com.enba.integrate.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.enba.integrate.springsecurity.bean.EnbaResult;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/** 未登陆时调用 */
@Component
public class EnbaAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException {
    //TODO 定义自己的错误码 和错误信息
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    httpServletResponse.getWriter().write(JSON.toJSONString(EnbaResult.err(500, "对不起，未登录")));
  }
}
