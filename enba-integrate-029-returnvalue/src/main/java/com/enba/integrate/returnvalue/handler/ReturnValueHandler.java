package com.enba.integrate.returnvalue.handler;

import com.enba.integrate.returnvalue.annotation.ApiSystemResponseData;
import com.enba.integrate.returnvalue.annotation.ApiSystemReturnResult;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class ReturnValueHandler implements HandlerMethodReturnValueHandler {

  private final RequestResponseBodyMethodProcessor target;

  public ReturnValueHandler(RequestResponseBodyMethodProcessor target) {
    this.target = target;
  }

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return target.supportsReturnType(returnType);
  }

  @Override
  /**
   * 处理返回值 本方法主要用于处理控制器方法的返回值，将其封装为统一的响应格式
   *
   * @param returnValue 控制器方法的返回值
   * @param returnType 方法的参数类型信息
   * @param mavContainer 模型和视图容器，用于存储方法的返回模型和视图
   * @param webRequest 当前的web请求对象
   * @throws Exception 处理过程中可能抛出的异常
   */
  public void handleReturnValue(
      Object returnValue,
      MethodParameter returnType,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest)
      throws Exception {
    // 检查控制器方法或其所在类上是否标注了@ApiSystemResponseData注解
    if (AnnotatedElementUtils.hasAnnotation(
            returnType.getContainingClass(), ApiSystemResponseData.class)
        || returnType.hasMethodAnnotation(ApiSystemResponseData.class)) {
      // 获取方法上的@ApiSystemResponseData注解
      ApiSystemResponseData apiSystemResponseDataOnMethod =
          AnnotationUtils.getAnnotation(returnType.getMethod(), ApiSystemResponseData.class);
      // 获取类上的@ApiSystemResponseData注解
      ApiSystemResponseData apiSystemResponseDataOnClass =
          AnnotationUtils.findAnnotation(
              returnType.getContainingClass(), ApiSystemResponseData.class);

      Class<? extends ApiSystemReturnResult> returnResultClass;
      // 确定使用方法上还是类上的@ApiSystemResponseData注解的resultClass属性
      if (null != apiSystemResponseDataOnMethod) {
        returnResultClass = apiSystemResponseDataOnMethod.resultClass();
      } else {
        returnResultClass = apiSystemResponseDataOnClass.resultClass();
      }

      // 如果返回值已经是ApiSystemReturnResult的实例，则直接处理
      if (returnValue instanceof ApiSystemReturnResult) {
        target.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        return;
      }

      // 将返回值封装为指定的返回结果类实例，并调用后续处理
      target.handleReturnValue(
          newInstance(returnResultClass).success(returnValue),
          returnType,
          mavContainer,
          webRequest);
    } else {
      // 如果没有@ApiSystemResponseData注解，直接处理原始返回值
      target.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
  }

  private ApiSystemReturnResult newInstance(
      Class<? extends ApiSystemReturnResult> returnResultClass)
      throws InstantiationException, IllegalAccessException {
    return returnResultClass.newInstance();
  }
}
