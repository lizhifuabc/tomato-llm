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
 * 默认配置类，配置了ChatClient、Document Reader和Text Splitter的基本功能。
 * 当应用启动时，ApplicationRunner将负责处理指定资源文件，
 * 通过TikaDocumentReader解析文档，将其切割为文本块，并将其存储在VectorStore中。
 *
 * @author lizhifu
 * @since 2024/9/11
 */
@Configuration
public class DefaultConfig {

    /**
     * 从配置文件中获取资源路径
     */
    @Value("${app.resource}")
    private Resource documentResource;

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        // 配置并返回一个ChatClient实例，用于后续聊天功能
        return chatClientBuilder.build();
    }

    @Bean
    ApplicationRunner applicationRunner(VectorStore vectorStore) {
        // 定义一个ApplicationRunner，应用启动后自动运行
        return args -> {
            // 创建一个TikaDocumentReader，用于读取指定的文档资源
            TikaDocumentReader documentReader = new TikaDocumentReader(documentResource);

            // 创建一个TokenTextSplitter，用于将文档分割为较小的文本块
            TextSplitter textSplitter = new TokenTextSplitter();

            // 将分割后的文档块存储到VectorStore中
            vectorStore.accept(
                    textSplitter.apply(
                            documentReader.get()));
        };
    }
}
