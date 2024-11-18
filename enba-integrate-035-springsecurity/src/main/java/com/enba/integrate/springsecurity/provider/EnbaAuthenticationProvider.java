package com.enba.integrate.springsecurity.provider;

import com.enba.integrate.springsecurity.service.EnbaUserDetailsService;
import com.enba.integrate.springsecurity.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/** 自定义安全认证 */
@Component
public class EnbaAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  EnbaUserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userName = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();

    UserDetails userInfo = userDetailsService.loadUserByUsername(userName);
    if (!PasswordEncoder.matches(password, userInfo.getPassword())) {
      throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
    }

    return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }
}
