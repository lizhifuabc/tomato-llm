package com.tomato.llm.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.tomato.llm.server.entity.ConnectConfig;
import cn.mybatis.mp.core.mybatis.mapper.MybatisMapper;

/**
 * <p>
 * 连接配置表 Mapper 接口
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-20
 */
@Mapper
public interface ConnectConfigMapper extends MybatisMapper<ConnectConfig> {
}
