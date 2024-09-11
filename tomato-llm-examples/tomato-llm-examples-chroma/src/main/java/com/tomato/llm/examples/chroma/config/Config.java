package com.tomato.llm.examples.chroma.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 配置类
 *
 * @author lizhifu
 * @since 2024/9/11
 */
@Configuration
@Slf4j
public class Config {

    /**
     * 启动函数，调用函数
     * @param catalog 函数目录
     * @return 启动函数
     */
    @Bean
    ApplicationRunner go(FunctionCatalog catalog) {
        Runnable composedFunction = catalog.lookup(null);
        return args -> {
            composedFunction.run();
        };
    }

    /**
     * 文档读取器函数，解析字节流为文档对象
     * @return 文档读取函数
     */
    @Bean
    Function<Flux<Message<byte[]>>, Flux<List<Document>>> documentReader() {
        return resourceFlux -> resourceFlux
                .map(message ->
                        new TikaDocumentReader(new ByteArrayResource(message.getPayload()))
                                .get()
                                .stream()
                                .peek(document -> {
                                    document.getMetadata()
                                            .put("source", message.getHeaders().get("file_name"));
                                })
                                .toList()
                );
    }

    /**
     * 文档分割器函数，将文档列表按一定规则进行分割
     * @return 分割函数
     */
    @Bean
    Function<Flux<List<Document>>, Flux<List<Document>>> splitter() {
        return documentListFlux ->
                documentListFlux
                        .map(unsplitList -> new TokenTextSplitter().apply(unsplitList));
    }

    /**
     * 向量存储消费者，将处理后的文档写入向量存储
     * @param vectorStore 向量存储
     * @return 消费者
     */
    @Bean
    Consumer<Flux<List<Document>>> vectorStoreConsumer(VectorStore vectorStore) {
        return documentFlux -> documentFlux
                .doOnNext(documents -> {
                    log.info("写入 {} 文档到向量存储", documents.size());
                    vectorStore.accept(documents);
                    log.info("{} 文档已经写入向量存储", documents.size());
                })
                // 订阅并触发流处理
                .subscribe();
    }
}
