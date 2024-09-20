package com.tomato.llm.server.config;

import com.tomato.llm.server.dao.ConnectConfigDao;
import com.tomato.llm.server.util.JdbcDataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源配置
 *
 * @author lizhifu
 * @since 2024/9/20
 */
@Configuration
@Slf4j
public class DatasourceConfig {
    @Resource
    private ConnectConfigDao connectConfigDao;
    @PostConstruct
    public void init() {
        // 初始化默认数据源
        // 从数据库中获取数据源配置
        connectConfigDao.getAll().forEach(config -> {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(config.getUrl());
            dataSource.setUsername(config.getUsername());
            dataSource.setPassword(config.getPassword());
            dataSource.setDriverClassName(config.getDriverClassName());
            JdbcDataSourceUtil.addDataSource(config.getName(), dataSource);
            log.info("初始化数据源：{}", config.getName());
        });
    }
}