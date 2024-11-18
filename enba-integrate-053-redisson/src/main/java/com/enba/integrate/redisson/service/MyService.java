package com.enba.integrate.redisson.service;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  @Autowired private RedissonClient redisson;

  public void process() {
    RLock lock = redisson.getLock("myLock");
    try {
      boolean canLock = lock.tryLock(0, 3, TimeUnit.SECONDS);
      if (!canLock) {
        System.out.println("Could not acquire lock.");
        return;
      }
      // 执行受保护的操作
      System.out.println("Executing protected operation...");
      // TODO 模拟业务执行耗时
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    } finally {
      if (lock.isHeldByCurrentThread()) {
        lock.unlock();
      }
    }
  }
}
