package com.enba.integrate.email.manager;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSendManager {

  private final JavaMailSender emailSender;

  public EmailSendManager(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  /**
   * 发送简单邮件
   *
   * @param to      收件人邮箱地址
   * @param subject 邮件主题
   * @param text    邮件内容
   */
  public void sendSimpleMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }
}