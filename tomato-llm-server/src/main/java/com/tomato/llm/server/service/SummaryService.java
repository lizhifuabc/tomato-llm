package com.tomato.llm.server.service;

/**
 * 生成摘要服务
 *
 * @author lizhifu
 * @since 2024/9/19
 */
public interface SummaryService {
    /**
     * 生成摘要
     *
     * @param content 内容
     * @return 摘要
     */
    String summary(String content);
}
