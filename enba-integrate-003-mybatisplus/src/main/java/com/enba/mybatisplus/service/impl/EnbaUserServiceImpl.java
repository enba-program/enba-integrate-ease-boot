package com.enba.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enba.mybatisplus.dao.EnbaUserDao;
import com.enba.mybatisplus.entity.EnbaUser;
import com.enba.mybatisplus.service.EnbaUserService;
import org.springframework.stereotype.Service;

/**
 * (EnbaUser)表服务实现类
 *
 * @author dingjunhua
 * @since 2024-08-02 01:03:58
 */
@Service("enbaUserService")
public class EnbaUserServiceImpl extends ServiceImpl<EnbaUserDao, EnbaUser> implements EnbaUserService {

}