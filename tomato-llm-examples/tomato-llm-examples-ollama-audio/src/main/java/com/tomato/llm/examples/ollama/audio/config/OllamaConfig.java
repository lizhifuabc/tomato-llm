package com.tomato.llm.examples.ollama.audio.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ollama 配置
 *
 * @author lizhifu
 * @since 2024/9/11
 */
@Configuration
public class OllamaConfig {
    @Bean
    ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
}
