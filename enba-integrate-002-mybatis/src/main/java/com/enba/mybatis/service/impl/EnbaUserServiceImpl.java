package com.enba.mybatis.service.impl;

import com.enba.mybatis.dao.EnbaUserDao;
import com.enba.mybatis.entity.EnbaUser;
import com.enba.mybatis.service.EnbaUserService;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * (EnbaUser)表服务实现类
 *
 * @author dingjunhua
 * @since 2024-08-02 00:11:50
 */
@Service("enbaUserService")
public class EnbaUserServiceImpl implements EnbaUserService {

  Logger log = LoggerFactory.getLogger(EnbaUserServiceImpl.class);


  @Resource
  private EnbaUserDao enbaUserDao;

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public EnbaUser queryById(Long id) {
    log.info("queryById###{}", id);
    return this.enbaUserDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  @Override
  public List<EnbaUser> queryAllByLimit(int offset, int limit) {
    return this.enbaUserDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param enbaUser 实例对象
   * @return 实例对象
   */
  @Override
  public EnbaUser insert(EnbaUser enbaUser) {
    this.enbaUserDao.insert(enbaUser);
    return enbaUser;
  }

  /**
   * 修改数据
   *
   * @param enbaUser 实例对象
   * @return 实例对象
   */
  @Override
  public EnbaUser update(EnbaUser enbaUser) {
    this.enbaUserDao.update(enbaUser);
    return this.queryById(enbaUser.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Long id) {
    return this.enbaUserDao.deleteById(id) > 0;
  }
}