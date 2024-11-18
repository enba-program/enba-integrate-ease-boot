package com.enba.druid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enba.druid.dao.EnbaUserDao;
import com.enba.druid.service.EnbaUserService;
import com.enba.druid.entity.EnbaUser;
import org.springframework.stereotype.Service;

/**
 * (EnbaUser)表服务实现类
 *
 * @author dingjunhua
 * @since 2024-08-02 01:03:58
 */
@Service("enbaUserService")
public class EnbaUserServiceImpl extends ServiceImpl<EnbaUserDao, EnbaUser> implements
    EnbaUserService {

}