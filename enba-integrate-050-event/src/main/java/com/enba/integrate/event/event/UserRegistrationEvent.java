package com.enba.integrate.event.event;

import org.springframework.context.ApplicationEvent;

public class UserRegistrationEvent extends ApplicationEvent {
  private final String username;

  public UserRegistrationEvent(Object source, String username) {
    super(source);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
