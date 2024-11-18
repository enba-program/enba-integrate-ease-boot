package com.enba.integrate.event.publisher;

import com.enba.integrate.event.event.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private ApplicationEventPublisher eventPublisher;

  public void registerUser(String username) {
    // 用户注册逻辑...

    // 注册成功后，发布一个事件
    eventPublisher.publishEvent(new UserRegistrationEvent(this, username));
  }
}
