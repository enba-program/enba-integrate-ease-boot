package com.enba.integrate.robot.config;

import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WechatRobot {

  private static RestTemplate restTemplate;

  public WechatRobot(RestTemplateBuilder builder) {
    WechatRobot.restTemplate = builder.build();
  }

  /**
   * @param webhookList 企业微信webhook地址集合
   * @param contentMsg  消息内容
   */
  public static void sendWechatRobot(Set<String> webhookList, String contentMsg) {
    // 入参准备
    Msg msg = new Msg();
    Content content = new Content();
    content.setContent(contentMsg);
    msg.setMsgtype("text").setText(content);

    webhookList.forEach(
        webhook -> {
          System.out.println("msg====" + JSON.toJSONString(msg));
          // 调用企业微信发送消息
          ResponseEntity<R> result = restTemplate.postForEntity(webhook, msg, R.class);
          if (result.hasBody() && "0".equals(result.getBody().getErrcode())) {
            log.info("send messge success contentMsg:{}", contentMsg);
          } else {
            log.error(
                "send messge error contentMsg:{}，result:{}", contentMsg, JSON.toJSONString(result));
          }
        });
  }

  @Data
  @Accessors(chain = true)
  public static class R {

    private String errcode;

    private String errmsg;
  }

  @Data
  @Accessors(chain = true)
  public static class Msg {

    private String msgtype;

    private Content text;
  }

  @Data
  @Accessors(chain = true)
  public static class Content {

    /**
     * 文本内容，最长不超过2048个字节，必须是utf8编码
     */
    private String content;

    /**
     * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用mentioned_mobile_list
     */
    private List<String> mentioned_list;

    /**
     * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
     */
    private List<String> mentioned_mobile_list;
  }
}
