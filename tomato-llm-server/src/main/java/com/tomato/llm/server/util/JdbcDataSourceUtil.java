package com.tomato.llm.server.util;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源工具
 *
 * @author lizhifu
 * @since 2024/9/20
 */
@Slf4j
public class JdbcDataSourceUtil {

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

    public static void getMetaData(JdbcTemplate jdbcTemplate) throws SQLException {
        // 获取数据库连接
        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
        // 获取数据库元数据
        var metaData = connection.getMetaData();
        // 获取数据库名称
        String databaseName = metaData.getDatabaseProductName();
        // 获取数据库版本
        String databaseVersion = metaData.getDatabaseProductVersion();
        // 获取数据库驱动名称
        String driverName = metaData.getDriverName();
        // 获取表的信息
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            log.info("tableName:{}", tableName);
        }
    }
}
