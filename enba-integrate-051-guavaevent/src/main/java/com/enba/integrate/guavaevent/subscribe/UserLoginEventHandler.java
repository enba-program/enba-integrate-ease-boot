package com.enba.integrate.guavaevent.subscribe;

import com.enba.integrate.guavaevent.event.UserLoginEvent;
import com.google.common.eventbus.Subscribe;

public class UserLoginEventHandler {

    @Subscribe
    public void handleUserLogin(UserLoginEvent event) {
        System.out.println("Handling login for user: " + event.getUsername());
        // 这里可以处理登录后的业务逻辑
    }
}