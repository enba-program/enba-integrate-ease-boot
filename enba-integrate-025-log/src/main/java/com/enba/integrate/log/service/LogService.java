package com.enba.integrate.log.service;

import com.enba.integrate.log.dao.AccessFuncLog.Log;

public interface LogService {

  void saveLog(Log dto);
}
