//package com.enba.log.upgrade.core;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//
//public class MysqlLogStorage implements LogStorage {
//
//  private final JdbcTemplate jdbcTemplate;
//
//  public MysqlLogStorage(JdbcTemplate jdbcTemplate) {
//    this.jdbcTemplate = jdbcTemplate;
//  }
//
//  @Override
//  public void store(String message) {
//    // 实现将日志存储到MySQL的逻辑
//    log.info("使用mysql存储日志:{}", message);
//  }
//}
