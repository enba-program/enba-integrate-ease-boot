package com.enba.springdatajpa.service;

import com.enba.springdatajpa.entity.EnbaUser;
import java.util.List;

/**
 * (EnbaUser)表服务接口
 *
 * @author dingjunhua
 * @since 2024-08-02 00:11:50
 */
public interface EnbaUserService {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  EnbaUser queryById(Long id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  List<EnbaUser> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param enbaUser 实例对象
   * @return 实例对象
   */
  EnbaUser insert(EnbaUser enbaUser);

  /**
   * 修改数据
   *
   * @param enbaUser 实例对象
   * @return 实例对象
   */
  EnbaUser update(EnbaUser enbaUser);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Long id);

}