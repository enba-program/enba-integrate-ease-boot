package com.enba.integrate.springsecurity.filter;

import com.enba.integrate.springsecurity.entity.EnbaUserDetails;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
//OncePerRequestFilter特点是在处理单个HTTP请求时确保过滤器的 doFilterInternal 方法只被调用一次
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
 /*
        //1.在请求头中获取token
        String token = request.getHeader("token");
 
        //此处需要判断token是否为空
        if (!StringUtils.hasText(token)){
            //没有token放行 此时的SecurityContextHolder没有用户信息 会被后面的过滤器拦截
            filterChain.doFilter(request,response);
            return;
        }
 
        //2.解析token获取用户id
*/

        //3.在redis中获取用户信息 注意：redis中的key是login：+userId
        EnbaUserDetails loginUser = new EnbaUserDetails();
 
        //此处需要判断loginUser是否为空
        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //4.将获取到的用户信息存入SecurityContextHolder 参数（用户信息，，权限信息）
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
 
        //5.放行
        filterChain.doFilter(request,response);
    }
}