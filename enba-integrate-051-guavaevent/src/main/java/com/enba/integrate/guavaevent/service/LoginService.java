package com.enba.integrate.guavaevent.service;

import com.enba.integrate.guavaevent.event.UserLoginEvent;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  @Autowired
  private EventBus eventBus;

  public void loginUser(String username) {
    // 登录逻辑...

    // 发布事件
    eventBus.post(new UserLoginEvent(username));
  }
}