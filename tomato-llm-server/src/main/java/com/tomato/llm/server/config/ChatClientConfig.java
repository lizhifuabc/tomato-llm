package com.tomato.llm.server.config;

import com.tomato.llm.server.dao.CustomPromptDao;
import com.tomato.llm.server.entity.CustomPrompt;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * chat client 配置
 * </p>
 *
 * @author lizhifu
 * @since 2024/9/19
 */
@Slf4j
@Configuration
public class ChatClientConfig {
    @Resource
    private CustomPromptDao customPromptDao;
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        log.info("chat client 配置");
        // 全局预设，也可以单独预设
        CustomPrompt defaultPrompt = customPromptDao.getDefaultPrompt();
        if (defaultPrompt != null) {
            chatClientBuilder.defaultSystem(defaultPrompt.getPrompt());
        }
        return chatClientBuilder.build();
    }
}