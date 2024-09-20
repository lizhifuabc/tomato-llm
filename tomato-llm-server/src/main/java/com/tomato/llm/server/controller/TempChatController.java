package com.tomato.llm.server.controller;

import com.tomato.llm.server.req.QuestionReq;
import com.tomato.llm.server.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * 对话
 *
 * @author lizhifu
 * @since 2024/9/18
 */
@Slf4j
@RestController
public class TempChatController {
    private final ChatService chatService;

    public TempChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/chat/temp/stream")
    @Operation(summary = "stream",description = "流式对话接口")
    public Flux<String> chatStreamTemp(@RequestBody QuestionReq questionReq) {
        return chatService.streamTempStr(questionReq);
    }
}
