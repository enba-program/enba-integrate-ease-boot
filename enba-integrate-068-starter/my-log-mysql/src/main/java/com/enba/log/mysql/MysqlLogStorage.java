package com.enba.log.mysql;

import com.enba.log.core.LogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MysqlLogStorage implements LogStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void storeLog(String logMessage) {
        String sql = "INSERT INTO logs (message, created_at) VALUES (?, NOW())";
        jdbcTemplate.update(sql, logMessage);
    }
}