package com.enba.integrate.i18n.handler;

import com.enba.integrate.i18n.content.I18nHolder;
import com.enba.integrate.i18n.model.I18nResource;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class I18nHandlerInterceptor implements HandlerInterceptor {

  private static final String LANGUAGE = "language";

  /**
   * 在处理请求之前，根据请求头中的语言信息设置国际化资源
   *
   * 该方法首先从请求头中获取语言信息（LANGUAGE），如果存在且不为空，
   * 则根据语言信息选择相应的资源包，并将其存入I18nHolder中以便后续使用。
   * 如果语言信息为英语，将使用美国英语（Locale.US）作为区域设置来获取资源包，
   * 否则将使用系统的默认区域设置来获取资源包。
   *
   * @param request HTTP请求对象，用于获取请求头中的语言信息
   * @param response HTTP响应对象，未在此方法中使用，但必须作为参数以满足接口要求
   * @param handler 处理请求的处理器对象，未在此方法中使用，但必须作为参数以满足接口要求
   * @return 返回true继续处理请求，或false中断请求处理链。此处总是返回true以继续处理请求
   * @throws Exception 如果发生异常将被抛出，但在实际代码中应当具体化异常类型
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // 从请求头中获取语言信息
    String language = request.getHeader(LANGUAGE);

    // 如果语言信息存在且不为空
    if (language != null && !"".equals(language)) {
      // 初始化资源包
      ResourceBundle bundle = null;
      // 如果语言信息为英语，使用美国英语作为区域设置获取资源包
      if (Locale.ENGLISH.getLanguage().equals(language)) {
        bundle = ResourceBundle.getBundle("i18n", Locale.US);
      } else {
        // 否则使用系统的默认区域设置获取资源包
        bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
      }
      // 创建并配置I18nResource对象
      I18nResource i18nResource = new I18nResource();
      i18nResource.setLanguage(language);
      i18nResource.setResourceBundle(bundle);
      // 将配置好的I18nResource存入I18nHolder中
      I18nHolder.putResourceBundle(i18nResource);
    }

    // 继续处理请求
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
      throws Exception {
    I18nHolder.clear();
  }
}
