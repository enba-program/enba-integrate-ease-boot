package com.enba.integrate.springretry.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  /**
   * 尝试执行某个操作，如果遇到异常将根据@Retryable注解的配置进行重试
   *
   * <p>此方法模拟了一个可能失败的操作，并且在模拟的失败条件下抛出异常 实际应用中， 抛出异常的条件应该是根据业务逻辑动态决定的，此处为了演示重试机制，使用了固定的条件
   */
  @Retryable(value = Exception.class, maxAttemptsExpression = "#{${retry.maxAttempts:3}}")
  public void doSomething() throws Exception {
    System.out.println("Trying to do something...");
    // 模拟异常条件，用于演示重试机制
    if (true) {
      // 在实际场景中，这个条件应该基于业务逻辑动态判断是否抛出异常
      throw new Exception("Something went wrong");
    }
  }

  // 定义恢复方法，当重试达到最大次数后调用
  @Recover
  public void recover(Exception e) {
    // 输出失败原因
    System.out.println("Failed after " + e.getMessage());
  }
}
