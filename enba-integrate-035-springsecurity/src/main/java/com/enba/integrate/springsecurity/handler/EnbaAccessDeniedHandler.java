package com.enba.integrate.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.enba.integrate.springsecurity.bean.EnbaResult;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/** 无权访问回调 */
@Component
public class EnbaAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AccessDeniedException e)
      throws IOException {
    //TODO 定义自己的错误码 和错误信息
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.getWriter().write(JSON.toJSONString(EnbaResult.err(500, "权限不足")));
  }
}
