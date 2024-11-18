package com.enba.integrate.robot.controller;

import com.enba.integrate.robot.config.DingRobot;
import com.enba.integrate.robot.config.WechatRobot;
import java.util.HashSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-msg")
public class EnbaMsgController {

  // 钉钉机器人通知地址
  private String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=da050d828de9dce09a5dc2224eb09b0371d375264e7f41e787ba6ebe6bec9996";

  private String wechatUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=cde25c23-8ecd-46ea-a8da-bfcb9a4da5d6";

  /**
   * 钉钉消息
   *
   * @return r
   */
  @GetMapping("/dingding")
  public String dingdingMsg() {
    return DingRobot.sendDingNotice(dingUrl, "异常：钉钉机器人消息");
  }

  /**
   * 企微消息发送成功
   *
   * @return r
   */
  @GetMapping("/wechat")
  public String wechatMsg() {
    WechatRobot.sendWechatRobot(new HashSet<String>() {{
      add(wechatUrl);
    }}, "企微机器人消息");
    return "企微消息发送成功";
  }


}
