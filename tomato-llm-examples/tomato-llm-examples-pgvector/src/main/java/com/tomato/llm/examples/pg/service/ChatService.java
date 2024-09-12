package com.tomato.llm.examples.pg.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * 聊天服务
 *
 * @author lizhifu
 * @since 2024/9/12
 */
@Service
public class ChatService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    @Value("classpath:/prompt-template.st")
    private Resource prompt;
    public ChatService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    public String chatWithDocument(String message) {
        return chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(promptUserSpec -> {
                    promptUserSpec.text(prompt);
                    promptUserSpec.param("input",message);
                })
                .call()
                .content();
    }
}
