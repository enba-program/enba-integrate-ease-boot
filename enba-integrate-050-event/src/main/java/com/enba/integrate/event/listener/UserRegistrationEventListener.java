package com.enba.integrate.event.listener;

import com.enba.integrate.event.event.UserRegistrationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationEventListener implements ApplicationListener<UserRegistrationEvent> {

  @Override
  public void onApplicationEvent(UserRegistrationEvent event) {
    String username = event.getUsername();
    System.out.println("User " + username + " has been registered.");
    // 进一步的处理，如发送欢迎邮件等...
  }
}
