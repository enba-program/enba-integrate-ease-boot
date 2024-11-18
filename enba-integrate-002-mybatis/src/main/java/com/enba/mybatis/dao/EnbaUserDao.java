package com.enba.mybatis.dao;

import com.enba.mybatis.entity.EnbaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (EnbaUser)表数据库访问层
 *
 * @author dingjunhua
 * @since 2024-08-02 00:11:49
 */
@Mapper
public interface EnbaUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EnbaUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<EnbaUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param enbaUser 实例对象
     * @return 对象列表
     */
    List<EnbaUser> queryAll(EnbaUser enbaUser);

    /**
     * 新增数据
     *
     * @param enbaUser 实例对象
     * @return 影响行数
     */
    int insert(EnbaUser enbaUser);

    /**
     * 修改数据
     *
     * @param enbaUser 实例对象
     * @return 影响行数
     */
    int update(EnbaUser enbaUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}