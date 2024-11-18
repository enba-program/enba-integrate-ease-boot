package com.enba.integrate.robot.config;

import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 发送钉钉消息工具类
 */
public class DingRobot {

  static final Logger log = LoggerFactory.getLogger(DingRobot.class);

  /**
   * 发送钉钉报警消息
   */
  public static String sendDingNotice(String url, String message) {
    try {
      ResponseEntity<String> result =
          new RestTemplate().postForEntity(url, getEntity(message), String.class);
      log.info(new String(result.getBody().getBytes(), "UTF-8"));
      return result == null ? null : result.getBody();
    } catch (Throwable e) {
      log.error("send ding message error, url={}", url, e);
    }
    return null;
  }

  /**
   * 发送钉钉报警消息
   */
  public static void sendDingNotice(Set<String> urlSet, String message) {
    urlSet.forEach(
        url -> {
          try {
            ResponseEntity<String> result =
                new RestTemplate().postForEntity(url, getEntity(message), String.class);
            log.info(new String(result.getBody().getBytes(), "UTF-8"));
          } catch (Throwable e) {
            log.error("send ding message error, url={}", url, e);
          }
        });
  }

  /**
   * 生成钉钉报警消息体
   */
  private static HttpEntity<String> getEntity(String message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    Map<String, Object> paramMap = new TreeMap<>();
    paramMap.put("msgtype", "text");
    Map<String, String> textMap = new TreeMap<>();
    textMap.put("content", message);
    paramMap.put("text", textMap);
    return new HttpEntity<>(JSON.toJSONString(paramMap), headers);
  }
}
