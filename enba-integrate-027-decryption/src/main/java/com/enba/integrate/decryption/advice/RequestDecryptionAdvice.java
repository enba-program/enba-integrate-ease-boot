package com.enba.integrate.decryption.advice;

import com.enba.integrate.decryption.annotation.RequestBodyDecryption;
import com.enba.integrate.decryption.annotation.RequestBodyDecryptionAlgorithm;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@RestControllerAdvice
public class RequestDecryptionAdvice implements RequestBodyAdvice, ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  /**
   * 重写supports方法，用于检查是否支持解密请求体
   *
   * @param methodParameter 表示当前操作（方法）的参数，包括其在方法签名中的位置和类型
   * @param targetType 对于泛型类型，表示目标返回类型，这里不使用
   * @param converterType 表示Http消息转换器的类型，这里用于指定转换器的界限
   * @return 返回是否支持给定方法参数、目标类型和转换器类型的解密操作
   */
  @Override
  public boolean supports(
      MethodParameter methodParameter,
      Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    // 获取方法参数对应的请求体解密注解
    RequestBodyDecryption requestBodyDecryption = getRequestBodyEncryption(methodParameter);
    // 如果没有解密注解，则不支持解密
    if (null == requestBodyDecryption) {
      return false;
    }

    // 返回解密操作是否成功执行
    return requestBodyDecryption.decryption();
  }

  /**
   * 获取请求体解密参数
   *
   * @param methodParameter 方法参数对象，用于获取RequestBodyDecryption注解
   * @return 返回RequestBodyDecryption对象，如果找不到则返回null
   */
  private RequestBodyDecryption getRequestBodyEncryption(MethodParameter methodParameter) {
    // 尝试从方法上获取RequestBodyDecryption注解
    RequestBodyDecryption paramRequestBodyDecryption =
        methodParameter.getMethodAnnotation(RequestBodyDecryption.class);

    // 如果方法上没有注解，尝试从类上获取注解
    if (null == paramRequestBodyDecryption) {
      RequestBodyDecryption classRequestBodyDecryption =
          methodParameter.getDeclaringClass().getAnnotation(RequestBodyDecryption.class);
      // 如果类上也找不到注解，则返回null
      if (null == classRequestBodyDecryption) {
        return null;
      }
      // 将类上的注解赋值给方法参数注解
      paramRequestBodyDecryption = classRequestBodyDecryption;
    }

    // 返回最终找到的RequestBodyDecryption注解
    return paramRequestBodyDecryption;
  }

  /**
   * 在读取请求体之前对请求进行处理 主要用于解密请求体，如果配置了相应的解密功能
   *
   * @param inputMessage 当前的HTTP输入消息
   * @param parameter 当前处理的请求参数信息
   * @param targetType 参数的目标类型
   * @param converterType 使用的HTTP消息转换器类型
   * @return 封装后的HTTP输入消息，可能包含解密后的请求体
   * @throws IOException 如果读取或写入操作过程中发生I/O错误
   */
  @Override
  public HttpInputMessage beforeBodyRead(
      HttpInputMessage inputMessage,
      MethodParameter parameter,
      Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType)
      throws IOException {

    // 获取请求参数上的请求体解密注解
    RequestBodyDecryption requestBodyDecryption = getRequestBodyEncryption(parameter);

    // 返回一个新的HTTP输入消息，该消息封装了原始消息并添加解密逻辑
    return new HttpInputMessage() {

      /**
       * 获取解密后的请求体 首先读取原始请求体，然后根据配置的解密算法进行解密
       *
       * @return 解密后的请求体输入流
       * @throws IOException 如果读取或写入操作过程中发生I/O错误
       */
      @Override
      public InputStream getBody() throws IOException {
        // 读取并转换原始请求体为字符串
        String bodyStr = IOUtils.toString(inputMessage.getBody(), "UTF-8");

        // 从Spring应用上下文中获取配置的解密算法实例
        RequestBodyDecryptionAlgorithm decryptionAlgorithm =
            applicationContext.getBean(requestBodyDecryption.decryptionClazz());

        // 使用解密算法解密请求体
        bodyStr = decryptionAlgorithm.decrypt(bodyStr, getHeaders());

        // 将解密后的请求体转换为输入流返回
        return IOUtils.toInputStream(bodyStr, "UTF-8");
      }

      /**
       * 获取HTTP请求头
       *
       * @return 请求头信息
       */
      @Override
      public HttpHeaders getHeaders() {
        // 返回原始请求消息的请求头
        return inputMessage.getHeaders();
      }
    };
  }

  /**
   * 重写afterBodyRead方法，用于在读取请求体后执行自定义逻辑
   *
   * @param body 读取到的请求体数据，尚未进行任何转换
   * @param inputMessage 包含请求信息和请求体的对象，可用于获取额外信息
   * @param parameter 当前请求处理方法的参数信息，用于指导如何解析请求体
   * @param targetType 请求处理方法的返回类型，用于指导如何转换请求体数据
   * @param converterType 执行转换的HttpMessageConverter的类型，用于指定使用哪种转换器
   * @return 该方法直接返回传入的body对象，未进行任何处理
   */
  @Override
  public Object afterBodyRead(
      Object body,
      HttpInputMessage inputMessage,
      MethodParameter parameter,
      Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {

    return body;
  }

  /**
   * 处理空请求体的方法 当请求体为空时，该方法被调用以确定如何处理这种情况 在当前实现中，该方法简单地返回传入的body对象，即使它为空
   *
   * @param body 请求体的内容如果请求体为空，这个参数可以是null
   * @param inputMessage Http输入消息，包含了请求的相关信息
   * @param parameter 当前处理方法的参数信息，用于指导如何解析和处理请求参数
   * @param targetType 转换目标类型，指定了希望将请求体内容转换成的类型
   * @param converterType 指定的Http消息转换器类型，用于帮助Spring MVC确定使用哪个消息转换器来处理请求体
   * @return Object 返回传入的body对象，这可能是因为在当前上下文中，直接返回null并不合适， 或者是为了保持方法签名的一致性，具体取决于应用逻辑
   */
  @Override
  public Object handleEmptyBody(
      Object body,
      HttpInputMessage inputMessage,
      MethodParameter parameter,
      Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {

    return body;
  }
}
