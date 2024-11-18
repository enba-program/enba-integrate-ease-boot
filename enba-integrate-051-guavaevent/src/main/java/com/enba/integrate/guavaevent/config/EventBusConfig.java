package com.enba.integrate.guavaevent.config;

import com.enba.integrate.guavaevent.subscribe.UserLoginEventHandler;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusConfig {

  @Bean
  public EventBus eventBus() {
    return new EventBus("MyEventBus");
  }

  @Bean
  public UserLoginEventHandler userLoginEventHandler(EventBus eventBus) {
    UserLoginEventHandler handler = new UserLoginEventHandler();
    eventBus.register(handler);
    return handler;
  }
}