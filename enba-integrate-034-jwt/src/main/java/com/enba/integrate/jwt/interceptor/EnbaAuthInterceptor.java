package com.enba.integrate.jwt.interceptor;

import com.enba.integrate.jwt.jwt.SecurityContextHolder;
import com.enba.integrate.jwt.jwt.JwtUser;
import com.enba.integrate.jwt.jwt.TokenManager;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/** 拦截器 */
@Slf4j
public class EnbaAuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = request.getHeader(SecurityContextHolder.TOKEN_KEY);
    if (StringUtils.hasLength(token)) {
      JwtUser jwtUser = TokenManager.checkToken(token);
      // 是否认证通过
      if (jwtUser.isValid()) {
        // 保存授权信息
        SecurityContextHolder.setUser(token, jwtUser);
        return true;
      }
    }

    // 返回json
    // 设置响应的内容类型为JSON
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    // 创建PrintWriter对象来写出JSON数据
    PrintWriter out = response.getWriter();
    String json = "{\"status\": \"error\", \"message\": \"unlogin\"}";
    out.print(json);
    out.flush();

    // 阻止请求继续传递
    return false;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    // 请求完成清除授权信息
    SecurityContextHolder.clearUser();
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
