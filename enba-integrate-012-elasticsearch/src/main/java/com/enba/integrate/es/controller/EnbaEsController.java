package com.enba.integrate.es.controller;

import com.alibaba.fastjson.JSON;
import com.enba.integrate.es.entity.User;
import com.enba.integrate.es.service.impl.EsUserServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-es")
@Slf4j
public class EnbaEsController {


  @Autowired
  private EsUserServiceImpl esUserService;

  /**
   * 创建索引
   */
  @GetMapping("/createIndex")
  public String createIndex() throws IOException {
    esUserService.createIndex();
    esUserService.getIndex();

    return "createIndex success...";
  }

  /**
   * 创建用户
   */
  @GetMapping("/createUser")
  public String createUser() throws IOException {
    User user = new User()
        .setUserId(123L)
        .setUsername("恩爸编程")
        .setAccount("account")
        .setCreatedDate(new Date())
        .setPassword("123456");
    esUserService.createUser(user);

    return "createUser success...";
  }

  /**
   * 批量创建
   */
  @GetMapping("/createUserBulk")
  public String createUserBulk() throws IOException {
    // 设置数据
    List<User> list = new ArrayList<>();
    list.add(new User().setUserId(2L).setUsername("恩爸编程1").setAccount("123456")
        .setCreatedDate(new Date()));
    list.add(
        new User().setUserId(3L).setUsername("恩爸编程2").setAccount("123456").setCreatedDate(new Date()));
    list.add(
        new User().setUserId(4L).setUsername("恩爸编程2").setAccount("123456").setCreatedDate(new Date()));
    list.add(
        new User().setUserId(5L).setUsername("恩爸编程4").setAccount("123456").setCreatedDate(new Date()));
    esUserService.createUserBulk(list);

    return "createUserBulk success...";
  }

  /**
   * 获取用户
   */
  @GetMapping("/getUser")
  public String getUser() throws IOException {
    User user = esUserService.getUser(1L);
    log.info("获取到用户为:{}", user);

    return "getUser success...";
  }

  /**
   * 查询用户
   */
  @GetMapping("/searchUser")
  public String searchUser() throws IOException {
    ArrayList<User> users = esUserService.searchUser();
    log.info("查询结果{}", JSON.toJSONString(users));

    return "searchUser success...";
  }

  /**
   * 更新用户
   */
  @GetMapping("/updateUser")
  public String updateUser() throws IOException {
    User user = new User()
        .setUserId(1L)
        .setUsername("恩爸编程")
        .setAccount("account")
        .setCreatedDate(new Date())
        .setPassword("123456");
    esUserService.updateUser(user);

    return "updateUser success...";
  }

  /**
   * 删除用户
   */
  @GetMapping("/deleteUser")
  public String deleteUser() throws IOException {
    esUserService.deleteUser(1L);

    return "deleteUser success...";
  }

}
