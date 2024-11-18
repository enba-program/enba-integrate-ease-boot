package com.enba.integrate.authorize.strategy;

import com.enba.integrate.authorize.annotation.AuthStrategy;
import com.enba.integrate.authorize.exception.DemoAuthException;
import java.util.Map;
import org.springframework.stereotype.Component;

/** 自定义鉴权策略 */
@Component
public class DemoAuthStrategy implements AuthStrategy<DemoAuthException> {

  @Override
  public boolean access(Map<String, Object> args) {
    // TODO 自定义鉴权策略逻辑,返回值改成true或者false 看下效果

    // 返回true:执行目标方法 | false:不执行目标方法并抛出以下异常
    return false;
  }

  @Override
  public DemoAuthException accessDeniedException(Map<String, Object> args) {
    return new DemoAuthException("抛出自定义业务异常");
  }
}
