package com.enba.hikaricp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enba.hikaricp.dao.EnbaUserDao;
import com.enba.hikaricp.service.EnbaUserService;
import com.enba.hikaricp.entity.EnbaUser;
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