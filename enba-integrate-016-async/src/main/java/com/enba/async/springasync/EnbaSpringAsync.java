package com.enba.async.springasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EnbaSpringAsync {

  Logger log = LoggerFactory.getLogger(getClass());

  @Async
  public void asyncMethod() {
    //  EnbaExecutorConfig类中的@Bean(name = "enbaExecutor")去掉执行此方法看效果
    log.info("使用spring封装的异步程池");
    log.info("使用spring封装的异步程池:" + Thread.currentThread().getName());
  }


  @Async(value = "enbaExecutor")
  public void asyncMethod2() {
    log.info("使用自定义线程池:" + Thread.currentThread().getName());
  }


}
