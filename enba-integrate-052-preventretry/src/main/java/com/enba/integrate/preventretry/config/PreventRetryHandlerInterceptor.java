package com.enba.integrate.preventretry.config;

import com.enba.integrate.preventretry.exception.PreventRetryException;
import com.enba.integrate.preventretry.props.PreventRetryProperties;
import com.enba.integrate.preventretry.service.CacheTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PreventRetryHandlerInterceptor implements HandlerInterceptor {

  private final Logger log = LoggerFactory.getLogger(PreventRetryHandlerInterceptor.class);

  private final PreventRetryProperties properties;

  private final CacheTemplate cacheTemplate;

  private static final String FILTER_METHOD = "POST";

  private static final String CACHE_REQ_ID_REDIS_KEY = "enba:prevent_retry:";

  public PreventRetryHandlerInterceptor(
      PreventRetryProperties properties, CacheTemplate cacheTemplate) {
    super();
    this.properties = properties;
    this.cacheTemplate = cacheTemplate;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!properties.isEnabled()) {
      return true;
    }

    // 仅对POST请求做防重复提交
    String method = request.getMethod();
    if (FILTER_METHOD.equals(method)) {
      String retryId = request.getHeader(properties.getPreventKey());
      if (StringUtils.hasText(retryId)) {
        String cacheKey = CACHE_REQ_ID_REDIS_KEY + retryId;
        Object object = cacheTemplate.get(cacheKey);
        if (object != null) {
          log.info("重复请求url==>" + request.getRequestURI());
          throw new PreventRetryException();
        }
        cacheTemplate.set(cacheKey, retryId, properties.getTtl());
      }
    }
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      @Nullable ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      @Nullable Exception ex)
      throws Exception {}
}
