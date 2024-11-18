package com.enba.integrate.returnvalue.handler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@Configuration
public class ReturnValueInitializer implements InitializingBean {

  @Autowired private RequestMappingHandlerAdapter adapter;

  /** 在所有属性设置完成后执行的初始化逻辑 主要用于自定义装饰返回值处理器列表 */
  @Override
  public void afterPropertiesSet() {
    // 获取原始的返回值处理器列表
    List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
    // 创建原始处理器列表的副本，以避免修改原始列表
    List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
    // 对处理器列表进行自定义装饰
    this.decorateHandlers(handlers);
    // 将装饰后的处理器列表设置回适配器
    adapter.setReturnValueHandlers(handlers);
  }

  /**
   * 装饰处理程序列表中的处理器，目的是为了在处理请求返回值时添加额外的处理逻辑
   *
   * @param handlers 处理器列表，用于处理控制器方法的返回值
   */
  private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    // 遍历处理器列表，寻找RequestResponseBodyMethodProcessor类型的处理器
    for (HandlerMethodReturnValueHandler handler : handlers) {
      // 当找到RequestResponseBodyMethodProcessor时，创建一个包装器对其进行装饰
      if (handler instanceof RequestResponseBodyMethodProcessor) {
        // 创建一个装饰器实例，传入当前的RequestResponseBodyMethodProcessor作为参数
        // 这里的装饰器可以用来添加额外的处理逻辑，例如日志记录或者错误处理
        ReturnValueHandler decorator =
            new ReturnValueHandler((RequestResponseBodyMethodProcessor) handler);
        // 获取装饰后的处理器在列表中的位置
        int index = handlers.indexOf(handler);
        // 用装饰后的处理器替换原始的处理器
        handlers.set(index, decorator);
        // 处理完第一个符合条件的处理器后，退出循环
        break;
      }
    }
  }
}
