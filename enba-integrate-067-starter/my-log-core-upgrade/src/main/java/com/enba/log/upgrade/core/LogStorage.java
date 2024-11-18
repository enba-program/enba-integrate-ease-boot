package com.enba.log.upgrade.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface LogStorage {

  Logger log = LoggerFactory.getLogger(LogStorage.class);

  void store(String message);
}
