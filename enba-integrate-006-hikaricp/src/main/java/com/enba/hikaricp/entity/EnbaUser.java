package com.enba.hikaricp.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (EnbaUser)表实体类
 *
 * @author dingjunhua
 * @since 2024-08-02 01:03:56
 */
@SuppressWarnings("serial")
public class EnbaUser extends Model<EnbaUser> {
    //主键
    private Long id;
    //名称
    private String userName;
    //年龄
    private Integer age;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //是否删除（0：未删除 1：删除）
    private Integer deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }