package com.tomato.llm.server.util;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源工具
 *
 * @author lizhifu
 * @since 2024/9/20
 */
public class JdbcDataSourceUtils {

    private static final Map<String, HikariDataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<>();

    public static HikariDataSource getDataSource(String name) {
        var data = DATA_SOURCE_MAP.get(name);
        if (data == null) {
            throw new RuntimeException("数据源不存在：" + name);
        }
        return data;
    }

    public static void addDataSource(String name, HikariDataSource dataSource) {
        DATA_SOURCE_MAP.putIfAbsent(name, dataSource);
    }

    public static JdbcTemplate jdbcTemplate(String name) {
        HikariDataSource dataSource = getDataSource(name);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // 设置数据源名称
        jdbcTemplate.setDatabaseProductName(name);
        // 设置查询结果集的最大行数为500
        jdbcTemplate.setFetchSize(500);
        return jdbcTemplate;
    }
}
