package com.tomato.llm.examples.pg.controller;

import com.tomato.llm.examples.pg.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聊天服务
 *
 * @author lizhifu
 * @since 2024/9/12
 */
@RestController
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping("/chat/doc")
    String chatWithDocument(String input) {
        return chatService.chatWithDocument(input);
    }
}
