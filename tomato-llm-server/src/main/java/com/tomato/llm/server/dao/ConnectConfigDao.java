package com.tomato.llm.server.dao;

import com.tomato.llm.server.entity.ConnectConfig;
import cn.mybatis.mp.core.mvc.Dao;

import java.util.List;

/**
 * <p>
 * 连接配置表 Dao 接口
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-20
 */
public interface ConnectConfigDao extends Dao<ConnectConfig,Long> {
    /**
     * 获取所有连接配置
     * @return 连接配置列表
     */
    List<ConnectConfig> getAll();
}
