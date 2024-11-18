package com.enba.redis.controller;

import com.enba.redis.entity.EnbaUser;
import com.enba.redis.utils.RedisUtils;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enba-redis")
public class RedisController {

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

    RedisUtils.save("enba:test:" + enbaUser.getId(), enbaUser);

    return "addTest success...";
  }


  @GetMapping("/get-test")
  public EnbaUser getTest() {
    EnbaUser enbaUser = RedisUtils.get("enba:test:" + id, EnbaUser.class);

    return enbaUser;
  }

}
