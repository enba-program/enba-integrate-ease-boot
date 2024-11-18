package com.enba.rabbitmq.controller;

import com.enba.rabbitmq.msg.EnbaUser;
import com.enba.rabbitmq.producer.EnbaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enbaUser")
public class EnbaRabbitmqController {

  @Autowired
  private EnbaProducer enbaProducer;

  private EnbaUser getEnbaUser() {
    EnbaUser enbaUser = new EnbaUser();
    enbaUser.setId(111L);
    enbaUser.setUserName("恩爸编程");
    enbaUser.setAge(20);
    enbaUser.setCreateTime(new Date());
    enbaUser.setUpdateTime(new Date());
    enbaUser.setDeleted(0);
    return enbaUser;
  }

  @GetMapping("/sendDirect")
  public String sendDirect() {
    enbaProducer.sendDirect(getEnbaUser());

    return "rabbitmq sendDirect...";
  }


  @GetMapping("/sendFanout")
  public String sendFanout() {
    enbaProducer.sendFanout(getEnbaUser());

    return "rabbitmq sendFanout...";
  }


  @GetMapping("/sendTopic")
  public String sendTopic() {
    enbaProducer.sendFanout(getEnbaUser());

    return "rabbitmq sendTopic...";
  }

  @GetMapping("/sendDelay")
  public String sendDelay() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    enbaProducer.sendDelay(objectMapper.writeValueAsString(getEnbaUser()),10);

    return "rabbitmq sendDelay...";
  }

  @GetMapping("/sendAndExpire")
  public String sendAndExpire() {
    enbaProducer.sendAndExpire(getEnbaUser());

    return "rabbitmq sendAndExpire...";
  }
}
