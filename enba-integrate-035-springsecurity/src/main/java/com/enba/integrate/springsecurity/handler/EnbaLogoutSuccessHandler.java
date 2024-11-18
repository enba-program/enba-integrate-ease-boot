package com.enba.integrate.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.enba.integrate.springsecurity.bean.EnbaResult;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/** 退出成功回调 */
@Component
public class EnbaLogoutSuccessHandler implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Authentication authentication)
      throws IOException {
    // TODO 结合自己的业务，做一些事情。比如清理redis中的用户信息
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.getWriter().write(JSON.toJSONString(EnbaResult.success("退出成功")));
  }
}
