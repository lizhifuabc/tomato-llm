package com.tomato.llm.examples.rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * 默认配置
 *
 * @author lizhifu
 * @since 2024/9/11
 */
@Configuration
public class DefaultConfig {

    @Value("${app.resource}")
    private Resource documentResource;

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

    @Bean
    ApplicationRunner applicationRunner(VectorStore vectorStore) {
        return args -> {
            TikaDocumentReader documentReader = new TikaDocumentReader(documentResource);
            TextSplitter textSplitter = new TokenTextSplitter();
            vectorStore.accept(
                    textSplitter.apply(
                            documentReader.get()));
        };
    }
}
