package com.enba.integrate.guavaevent.event;

public class UserLoginEvent {

  private final String username;

  public UserLoginEvent(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}