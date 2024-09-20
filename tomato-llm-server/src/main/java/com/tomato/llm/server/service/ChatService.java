package com.tomato.llm.server.service;

import com.tomato.llm.server.req.QuestionReq;
import reactor.core.publisher.Flux;

/**
 * 对话
 *
 * @author lizhifu
 * @since 2024/9/18
 */
public interface ChatService {

    Flux<String> streamTempStr(QuestionReq questionReq);
}
