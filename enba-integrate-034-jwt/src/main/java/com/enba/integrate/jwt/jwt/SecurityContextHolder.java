package com.enba.integrate.jwt.jwt;

import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SecurityContextHolder {

  @ApiModelProperty("请求头token的下标")
  public static final String TOKEN_KEY = "token";

  /** 模拟session */
  private static final HashMap<String, JwtUser> JWT_USER = new HashMap<String, JwtUser>();

  /**
   * 全局获取用户信息 通过解析HTTP请求中的JWT令牌来获取用户信息 主要用于需要在全局范围内获取用户信息的场景
   *
   * @return JwtUser 用户信息，如果请求中没有指定的令牌，则返回null
   */
  public static JwtUser getUser() {
    // 从ServletRequestAttributes中获取HttpServletRequest对象
    HttpServletRequest request =
        ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
            .getRequest();

    // 通过请求头中的TOKEN_KEY获取对应的JWTUser对象
    return JWT_USER.get(request.getHeader(TOKEN_KEY));
  }

  /**
   * 设置用户信息到缓存中
   *
   * @param token 用户的令牌，用作缓存的键
   * @param user 用户对象，包含用户的相关信息
   */
  public static void setUser(String token, JwtUser user) {
    JWT_USER.put(token, user);
  }

  /** 清除授权 通过移除存储在请求头中的令牌，来撤销对用户的授权状态 */
  public static void clearUser() {
    // 获取当前的HttpServletRequest对象，用于访问请求头
    HttpServletRequest request =
        ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
            .getRequest();
    // 根据请求头中的令牌Key，移除对应的令牌信息，实现授权清除
    JWT_USER.remove(request.getHeader(TOKEN_KEY));
  }
}
