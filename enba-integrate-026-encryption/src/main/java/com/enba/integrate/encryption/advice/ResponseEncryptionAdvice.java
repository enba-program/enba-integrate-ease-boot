package com.enba.integrate.encryption.advice;

import com.enba.integrate.encryption.annotation.ResponseBodyEncryption;
import com.enba.integrate.encryption.annotation.ResponseBodyEncryptionAlgorithm;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseEncryptionAdvice
    implements ResponseBodyAdvice<Object>, ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  /**
   * 重写supports方法，用于判断是否支持消息转换器的加密功能
   *
   * @param methodParameter 表示当前方法的参数，用于获取ResponseBodyEncryption注解信息
   * @param aClass HttpMessageConverter的子类类型，这里未具体使用
   * @return 返回是否支持消息转换器的加密功能
   */
  @Override
  public boolean supports(
      MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    // 获取方法参数上的ResponseBodyEncryption注解信息
    ResponseBodyEncryption responseBodyEncryption = getRequestBodyEncryption(methodParameter);
    // 如果ResponseBodyEncryption注解信息为空，则不支持消息转换器的加密功能，返回false
    if (null == responseBodyEncryption) {
      return false;
    }

    // 返回ResponseBodyEncryption注解的encryption方法值，表示是否支持消息转换器的加密功能
    return responseBodyEncryption.encryption();
  }

  /**
   * 获取请求体加密配置 优先从方法级别获取RequestBodyEncryption注解，如果不存在，则从类级别获取
   *
   * @param methodParameter 方法参数对象，用于获取注解信息
   * @return 返回请求体加密配置，如果存在的话；否则返回null
   */
  private ResponseBodyEncryption getRequestBodyEncryption(MethodParameter methodParameter) {
    // 尝试从方法级别获取RequestBodyEncryption注解
    ResponseBodyEncryption paramResponseBodyEncryption =
        methodParameter.getMethodAnnotation(ResponseBodyEncryption.class);

    // 如果方法级别没有该注解，则尝试从类级别获取
    if (null == paramResponseBodyEncryption) {
      // 从类级别获取RequestBodyEncryption注解
      ResponseBodyEncryption classResponseBodyEncryption =
          methodParameter.getDeclaringClass().getAnnotation(ResponseBodyEncryption.class);
      // 如果类级别也不存在该注解，则返回null
      if (null == classResponseBodyEncryption) {
        return null;
      }
      // 将类级别的注解赋值给方法级别的变量
      paramResponseBodyEncryption = classResponseBodyEncryption;
    }

    // 返回请求体加密配置
    return paramResponseBodyEncryption;
  }

  /**
   * 在响应体写入之前加密响应体
   *
   * <p>此方法的目的是在控制器方法执行完毕后，对响应体进行加密处理它是在Spring框架的拦截机制中实现的，
   * 用于处理响应数据在发送给客户端之前，根据特定的加密算法对响应数据进行加密，以保护数据的安全性
   *
   * @param result 控制器方法的返回结果，即需要加密的数据
   * @param methodParameter 描述了返回结果的类型信息
   * @param mediaType 媒体类型，描述了返回结果的媒体格式
   * @param aClass 描述了消息转换器的类型信息
   * @param serverHttpRequest 服务器HTTP请求对象，包含了请求的所有信息
   * @param serverHttpResponse 服务器HTTP响应对象，用于发送响应数据给客户端
   * @return 加密后的响应体
   */
  @Override
  public Object beforeBodyWrite(
      Object result,
      MethodParameter methodParameter,
      MediaType mediaType,
      Class<? extends HttpMessageConverter<?>> aClass,
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {

    // 获取请求体加密配置信息
    ResponseBodyEncryption responseBodyEncryption = getRequestBodyEncryption(methodParameter);

    // 根据配置信息，从Spring容器中获取对应的加密算法实例
    ResponseBodyEncryptionAlgorithm encryptionAlgorithm =
        applicationContext.getBean(responseBodyEncryption.encryptionClazz());

    // 使用加密算法对响应体进行加密，并返回加密后的结果
    return encryptionAlgorithm.encrypt(
        result, serverHttpRequest.getHeaders(), serverHttpResponse.getHeaders());
  }
}
