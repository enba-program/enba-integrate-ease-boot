package com.enba.rocketmq.controller;


import com.enba.rocketmq.constants.RocketMqTopicConstant;
import com.enba.rocketmq.msg.EnbaUser;
import com.enba.rocketmq.producer.EnbaProducer;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (EnbaUser)表控制层
 *
 * @author dingjunhua
 * @since 2024-08-02 00:11:51
 */
@RestController
@RequestMapping("/enbaUser")
public class EnbaUserController {

  @Autowired
  private EnbaProducer enbaProducer;


  @GetMapping("pub-msg")
  public String pubMsg() {
    EnbaUser enbaUser = new EnbaUser();
    enbaUser.setId(111L);
    enbaUser.setUserName("恩爸编程");
    enbaUser.setAge(20);
    enbaUser.setCreateTime(new Date());
    enbaUser.setUpdateTime(new Date());
    enbaUser.setDeleted(0);

    enbaProducer.sendMessage(RocketMqTopicConstant.TOPIC_TEST, enbaUser);

    return "rocketmq pubMsg success...";
  }


}