package com.enba.integrate.resttemplate.handler;

import com.enba.integrate.resttemplate.exception.ClientErrorException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.server.ServerErrorException;

/** 自定义异常拦截器 */
@Component
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

  /**
   * 处理HTTP响应中出现的错误
   *
   * @param response 客户端接收的HTTP响应对象
   * @throws IOException 如果在处理响应时发生输入输出异常
   */
  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    // 检查HTTP状态码是否属于客户端错误系列
    if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
      // 抛出包含具体状态码的客户端错误异常
      throw new ClientErrorException("Client error occurred: " + response.getStatusCode());
    }
    // 检查HTTP状态码是否属于服务器错误系列
    else if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
      // 抛出包含具体状态码的服务器错误异常
      throw new ServerErrorException("Server error occurred: " + response.getStatusCode());
    }
  }

  /**
   * 判断HTTP响应中是否包含错误
   *
   * <p>此方法用于分析HTTP客户端响应，确定该响应是否表示一个成功的请求（即状态码为2xx系列） 它通过检查响应状态码来判断，如果状态码不属于2xx系列，则假定响应中包含错误
   *
   * @param response HTTP客户端响应
   * @return 如果响应中包含错误，则返回true；否则返回false
   * @throws IOException 如果在读取响应时发生I/O错误
   */
  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return !response.getStatusCode().is2xxSuccessful();
  }
}
