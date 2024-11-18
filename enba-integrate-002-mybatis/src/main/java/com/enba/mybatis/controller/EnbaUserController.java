package com.enba.mybatis.controller;

import com.enba.mybatis.entity.EnbaUser;
import com.enba.mybatis.service.EnbaUserService;
import javax.annotation.Resource;
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


  /**
   * 服务对象
   */
  @Resource
  private EnbaUserService enbaUserService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("/selectOne")
  public EnbaUser selectOne(Long id) {
    return this.enbaUserService.queryById(id);
  }

}