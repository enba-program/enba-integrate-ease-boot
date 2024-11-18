package com.enba.integrate.smartdoc.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 恩爸编程
 */
@RestController
@RequestMapping(value = "/enba-smart-doc")
public class EnbaSmartDocController {

  /**
   * hello
   * @return String
   */
  @RequestMapping(value = "/hello")
  public String hello() {
    return "hello world";
  }

  /**
   * Test @RequestParam
   * @param author 作者|村上春树
   * @param type   type
   */
  @GetMapping("testRequestParam")
  public void testRequestParam(@RequestParam String author, @RequestParam String type) {

  }

  /**
   * 查询用户信息
   * @param name 用户名
   * @apiNote 通过用户的名称去查询到用户的详细信息
   * @return
   */
  @PostMapping(value = "/query")
  public String resp(@RequestBody String name){
    return null;
  }


  /**
   * 查询用户信息
   * @param name 用户名
   * @apiNote 通过用户的名称去查询到用户的详细信息
   * @deprecated
   * @return
   */
  @PostMapping(value = "/query")
  public String resp2(@RequestBody String name){
    return null;
  }

  /**
   * 查询用户信息
   * @param name 用户名
   * @apiNote 通过用户的名称去查询到用户的详细信息
   * @since v2.1.0
   * @return
   */
  @PostMapping(value = "/query")
  public String resp3(@RequestBody String name){
    return null;
  }

  /**
   * 返回用户信息
   * @return
   */
  @GetMapping(value = "/user")
  public JSONObject object() {
    return null;
  }

}
