package com.enba.integrate.email.controller;

import com.enba.integrate.email.manager.EmailSendManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-email")
public class EnbaEmailController {

  @Autowired
  private EmailSendManager emailSendManager;

  @GetMapping("/send-email")
  public String sendEmail() {

    emailSendManager.sendSimpleMessage("recipient@example.com", "Hello from Spring Boot!",
        "This is a test email.");

    return "Email sent successfully.";
  }


}
