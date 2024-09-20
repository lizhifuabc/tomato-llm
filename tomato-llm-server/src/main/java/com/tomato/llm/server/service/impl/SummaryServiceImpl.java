package com.tomato.llm.server.service.impl;

import com.tomato.llm.server.service.SummaryService;
import org.springframework.stereotype.Service;

/**
 * 生成摘要服务
 *
 * @author lizhifu
 * @since 2024/9/19
 */
@Service
public class SummaryServiceImpl implements SummaryService {
    @Override
    public String summary(String content) {
        // 截取前10个字符
        // TODO 大模型接口生成摘要
        return content.substring(0, 10);
    }
}
