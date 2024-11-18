package com.enba.sms.controller;

import com.enba.sms.manager.AliSmsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-sms")
public class EnbaSmsController {

  private AliSmsManager aliSmsManager;

  /**
   * 发送短信
   *
   * @return
   */
  @GetMapping("/send-sms")
  public String sendSms() {
    // 这三个替换成自己的
    String phoneNumber = "15981656787";

    String templateCode = "SMS_787656590";

    String smsCode = "123456";

    // 发送短信
    aliSmsManager.sendSms(phoneNumber, templateCode, smsCode);

    return "sendSms success...";
  }

}
