package com.tomato.llm.examples.ollama.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 聊天控制器
 *
 * @author lizhifu
 * @since 2024/9/11
 */
@RestController
@RequestMapping("/model")
public class ChatModelController {

    private final ChatModel chatModel;

    ChatModelController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    
    @GetMapping("/chat")
    String chat(@RequestParam(defaultValue = "唐诗三百首") String question) {
        return chatModel.call(question);
    }

    @GetMapping("/chat/generic-options")
    String chatWithGenericOptions(@RequestParam(defaultValue = "唐诗三百首") String question) {
        return chatModel.call(new Prompt(question, ChatOptionsBuilder.builder()
                        .withTemperature(0.9f)
                        .build()))
                .getResult().getOutput().getContent();
    }

    @GetMapping("/chat/provider-options")
    String chatWithProviderOptions(@RequestParam(defaultValue = "唐诗三百首") String question) {
        return chatModel.call(new Prompt(question, OllamaOptions.create()
                        .withModel("mistral")
                        .withRepeatPenalty(1.5f)))
                .getResult().getOutput().getContent();
    }

    @GetMapping("/chat/stream")
    Flux<String> chatStream(@RequestParam(defaultValue = "唐诗三百首") String question) {
        return chatModel.stream(question);
    }
}
