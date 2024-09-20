package com.tomato.llm.server.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

/**
 *
 *
 * @author lizhifu
 * @since 2024/9/20
 */
@SpringBootTest
public class JdbcDataSourceUtilTest {
    @Test
    public void test() throws SQLException {
        var jdbcTemplate = JdbcDataSourceUtil.jdbcTemplate("zjw");
        JdbcDataSourceUtil.getMetaData(jdbcTemplate);
    }
}
