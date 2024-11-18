package com.enba.integrate.jwt.controller;

import com.enba.integrate.jwt.jwt.JwtUser;
import com.enba.integrate.jwt.jwt.PasswordEncoder;
import com.enba.integrate.jwt.jwt.TokenManager;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-jwt")
public class EnbaJwtController {

  // 模拟从数据库获得的账号密码
  private static final HashMap<String, String> USER =
      new HashMap<String, String>() {
        {
          // $2a$10$bj2BGw7AAmuCzXmUSF/U0O24GK6vaUurU8525jkeuuV0QZTcp20P6 【明文是123456】
          put("enba", "$2a$10$bj2BGw7AAmuCzXmUSF/U0O24GK6vaUurU8525jkeuuV0QZTcp20P6");
        }
      };

  @GetMapping("/login/no-need-login")
  @ApiOperation("演示不需要登陆就能访问")
  public String login_no_need_login() {
    return "不需要登陆就能访问";
  }

  @GetMapping("/unlogin")
  @ApiOperation("模拟拦截")
  public String getInfo() {
    return "模拟拦截";
  }

  @GetMapping("/login")
  @ApiOperation("模拟登陆接口")
  public String login(String username, String password) {
    if (PasswordEncoder.matches(password, USER.get(username))) {
      // 模拟一个用户的数据 用户id为1  登录端为网页web  角色是admin
      return TokenManager.createToken("1", "web", "admin");
    }
    return "error";
  }

  @GetMapping("/check-token")
  @ApiOperation("校验token是否合法")
  public JwtUser tokenValidate(String token) {
    return TokenManager.checkToken(token);
  }
}
