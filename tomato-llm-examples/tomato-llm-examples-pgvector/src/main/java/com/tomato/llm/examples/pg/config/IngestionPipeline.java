package com.tomato.llm.examples.pg.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author lizhifu
 * @since 2024/9/12
 */
@Slf4j
@Configuration
public class IngestionPipeline {
    private final VectorStore vectorStore;

    @Value("classpath:documents/story1.md")
    Resource textFile1;

    @Value("classpath:documents/story2.txt")
    Resource textFile2;

    public IngestionPipeline(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void run() {
        List<Document> documents = new ArrayList<>();

        log.info("正在加载。md文件作为文档");
        var textReader1 = new TextReader(textFile1);
        // 添加自定义元数据 "location": "North Pole"，表示文档的来源或关联位置。
        textReader1.getCustomMetadata().put("location", "North Pole");
        textReader1.setCharset(Charset.defaultCharset());
        documents.addAll(textReader1.get());

        log.info("正在加载。txt文件作为文档");
        var textReader2 = new TextReader(textFile2);
        // 为该文件添加自定义元数据 "location": "Italy"。
        textReader2.getCustomMetadata().put("location", "Italy");
        textReader2.setCharset(Charset.defaultCharset());
        documents.addAll(textReader2.get());

        log.info("从文档创建和存储嵌入");
        vectorStore.add(new TokenTextSplitter().split(documents));
    }
}
