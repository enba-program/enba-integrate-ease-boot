package com.enba.integrate.jwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiModelProperty;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/** token生成，校验 */
@Slf4j
public class TokenManager {

  @ApiModelProperty("盐")
  private static final String SALT = "enba";

  @ApiModelProperty("令牌有效期毫秒")
  private static final long TOKEN_VALIDITY = 1000 * 60 * 60 * 24;

  @ApiModelProperty("Base64 密钥")
  private static final String SECRET_KEY =
      Base64.getEncoder().encodeToString(SALT.getBytes(StandardCharsets.UTF_8));

  /**
   * 创建JWT（Json Web Token） 该方法用于根据用户ID、客户端ID和角色创建一个具有有效期的JWT令牌
   *
   * @param userId 用户ID，用于标识令牌的所有者
   * @param clientId 客户端ID，用于标识令牌的接收者
   * @param role 用户角色，用于存储用户的权限信息
   * @return 返回创建的JWT令牌字符串
   */
  public static String createToken(String userId, String clientId, String role) {
    // 设置令牌的有效期，TOKEN_VALIDITY应为一个常量，表示令牌的有效时间（毫秒）
    Date validity = new Date((new Date()).getTime() + TOKEN_VALIDITY);

    // 使用Jwts.builder()创建JWT构建器，开始构建JWT
    return Jwts.builder()
        // 设置JWT的主体，通常是所有者的ID
        .setSubject(String.valueOf(userId))
        // 设置JWT的签发者，这里设置为空字符串，可以根据需要更改
        .setIssuer("")
        // 设置JWT的签发时间
        .setIssuedAt(new Date())
        // 设置JWT的接收者，通常为客户端ID
        .setAudience(clientId)
        // 设置JWT的自定义声明，可以存放业务数据，如用户ID和角色
        .claim("userId", userId)
        .claim("role", role)
        // 使用HS256算法和密钥签名JWT
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        // 设置JWT的有效期
        .setExpiration(validity)
        // 构建并返回JWT令牌字符串
        .compact();
  }

  /**
   * 校验Token的有效性
   *
   * <p>该方法用于校验给定的Token是否有效。如果Token有效，它将解析Token中的信息， 如用户ID和角色，并返回一个包含这些信息的JwtUser对象。
   *
   * @param token 待校验的Token字符串
   * @return 如果Token有效，则返回一个包含用户信息的JwtUser对象；否则，返回一个默认的JwtUser对象
   */
  public static JwtUser checkToken(String token) {
    // 首先校验Token是否有效
    if (validateToken(token)) {
      // 使用秘钥解析Token，并获取Token内的信息主体
      Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
      // 从Token中获取受众，这里受众可以理解为接收该Token的应用或服务的标识
      String audience = claims.getAudience();
      // 获取Token中的用户ID
      String userId = claims.get("userId", String.class);
      // 获取Token中的用户角色
      String role = claims.get("role", String.class);
      // 创建JwtUser对象，填充从Token中获取的用户信息，并标记该Token为有效
      JwtUser jwtUser = new JwtUser().setUserId(userId).setRole(role).setValid(true);
      // 记录Token校验信息，包括用户信息和受众
      log.info("===token有效{},客户端{}", jwtUser, audience);
      // 返回包含有效用户信息的JwtUser对象
      return jwtUser;
    }

    // TODO 这里直接返回，也可以结合自己业务抛出异常
    return new JwtUser();
  }

  /**
   * 验证JWT令牌是否有效
   *
   * <p>此方法用于验证传递的JWT令牌是否为有效令牌它通过尝试解析JWT并验证其签名来完成 如果令牌有效且能被正确解析，则方法返回true；如果解析过程中发生异常，则认为令牌无效
   *
   * @param authToken 用户的JWT令牌
   * @return 如果令牌验证通过返回true，否则返回false
   */
  private static boolean validateToken(String authToken) {
    try {
      // 使用SECRET_KEY作为密钥解析JWT令牌如果令牌有效，parseClaimsJws方法不会抛出异常
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
      return true;
    } catch (Exception e) {
      // TODO 根据业务需要，这里也可以抛出自定义的异常
      log.error("token无效:{}", authToken);
    }
    return false;
  }
}
