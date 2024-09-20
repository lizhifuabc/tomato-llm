package com.tomato.llm.server.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tomato.llm.server.service.CustomPromptService;
import com.tomato.llm.server.entity.CustomPrompt;
import com.tomato.llm.server.dao.CustomPromptDao;

/**
 * <p>
 * 自定义提示词 Service 实现类
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-19
 */
@Service
public class CustomPromptServiceImpl implements CustomPromptService {

    @Autowired
    private CustomPromptDao customPromptDao;
}
