package com.enba.integrate.springsecurity.service;

import com.enba.integrate.springsecurity.entity.EnbaUserDetails;
import com.enba.integrate.springsecurity.utils.PasswordEncoder;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EnbaUserDetailsService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 根据用户名加载用户信息，若用户名不存在则抛出异常

    // TODO 模拟从数据库中获取用户信息的过程,这里结合业务改成从数据库获取用户信息
    EnbaUserDetails userInfo = new EnbaUserDetails();
    userInfo.setUsername(username);
    userInfo.setPassword(PasswordEncoder.encode("123")); // 假设用户密码为"123"且经过加密处理

    // 初始化权限集合
    Set authSet = new HashSet();
    // 模拟从数据库获取用户角色，此处假设用户角色为"ROLE_ADMIN"
    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

    // 将用户角色添加到权限集合中
    authSet.add(authority);
    // 将权限集合设置到用户信息中
    userInfo.setAuthorities(authSet);

    // 返回包含用户信息及权限的UserDetails对象
    return userInfo;
  }
}
