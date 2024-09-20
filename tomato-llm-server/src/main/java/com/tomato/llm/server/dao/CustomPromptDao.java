package com.tomato.llm.server.dao;

import com.tomato.llm.server.entity.CustomPrompt;
import cn.mybatis.mp.core.mvc.Dao;

/**
 * <p>
 * 自定义提示词 Dao 接口
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-19
 */
public interface CustomPromptDao extends Dao<CustomPrompt,Long> {
    /**
     * 获取默认提示词
     * @return 默认提示词
     */
    CustomPrompt getDefaultPrompt();
}
