package com.enba.integrate.log.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.enba.integrate.log.dao.AccessFuncLog.Log;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

  @Override
  public void saveLog(Log dto) {

    // TODO 这里只是简单打印
    // TODO 实际可以记录到自己的存储层。如 mysql,mongodb等

    System.out.println("保存日志到数据库" + JSON.toJSONString(dto, SerializerFeature.WriteMapNullValue));
  }
}
