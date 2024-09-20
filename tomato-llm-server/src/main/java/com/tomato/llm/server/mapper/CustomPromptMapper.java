package com.tomato.llm.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.tomato.llm.server.entity.CustomPrompt;
import cn.mybatis.mp.core.mybatis.mapper.MybatisMapper;

/**
 * <p>
 * 自定义提示词 Mapper 接口
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-19
 */
@Mapper
public interface CustomPromptMapper extends MybatisMapper<CustomPrompt> {
}
