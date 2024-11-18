package com.enba.starter.usedemo;

import com.enba.log.upgrade.core.LogService;
import org.springframework.stereotype.Component;

/** 模拟项目中自定义LogService实现并注入spring容器 */
@Component
public class UserLogService extends LogService {

  public UserLogService() {
    super(null);
  }

  @Override
  public void log(String message) {
    System.out.println("用户自定义日志存储策略");
  }
}
