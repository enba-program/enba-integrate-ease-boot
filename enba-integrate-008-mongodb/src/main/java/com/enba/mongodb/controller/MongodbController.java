package com.enba.mongodb.controller;

import com.enba.mongodb.document.EnbaUser;
import com.enba.mongodb.repository.EnbaRepository;
import com.enba.mongodb.service.UserServiceImpl;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("enba-mongodb")
public class MongodbController {

  @Autowired
  private EnbaRepository enbaRepository;

  @Autowired
  private UserServiceImpl userServiceImpl;

  private Long id = 111L;

  @GetMapping("/add-test")
  public String addTest() {
    EnbaUser enbaUser = new EnbaUser();
    enbaUser.setId(id);
    enbaUser.setUserName("恩爸编程");
    enbaUser.setAge(20);
    enbaUser.setCreateTime(new Date());
    enbaUser.setUpdateTime(new Date());
    enbaUser.setDeleted(0);

    userServiceImpl.insertUser(enbaUser);

    return "addTest success...";
  }


  @GetMapping("/get-test")
  public EnbaUser getTest() {
    EnbaUser user = userServiceImpl.getUser(id);

    return user;
  }


  @GetMapping("/add-test-2")
  public String addTest2() {
    EnbaUser enbaUser = new EnbaUser();
    enbaUser.setId(222L);
    enbaUser.setUserName("恩爸编程2222");
    enbaUser.setAge(30);
    enbaUser.setCreateTime(new Date());
    enbaUser.setUpdateTime(new Date());
    enbaUser.setDeleted(1);

    enbaRepository.save(enbaUser);

    return "addTest2 success...";
  }

  @GetMapping("/get-test-2")
  public EnbaUser getTest2() {
    EnbaUser user = enbaRepository.findByUserName("恩爸编程");

    return user;
  }

}
