package com.tomato.llm.server.service.impl;

import com.tomato.llm.server.req.QuestionReq;
import com.tomato.llm.server.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天服务
 *
 * @author lizhifu
 * @since 2024/9/18
 */
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final ChatClient chatClient;

    public ChatServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Flux<String> streamTempStr(QuestionReq questionReq) {
        return chatClient.prompt().user(questionReq.question()).stream().content();
    }
}
