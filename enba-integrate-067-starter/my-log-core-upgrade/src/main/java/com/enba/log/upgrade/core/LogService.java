package com.enba.log.upgrade.core;

public class LogService {

  private final LogStorage logStorage;

  public LogService(LogStorage logStorage) {
    this.logStorage = logStorage;
  }

  public void log(String message) {
    logStorage.store(message);
  }
}
