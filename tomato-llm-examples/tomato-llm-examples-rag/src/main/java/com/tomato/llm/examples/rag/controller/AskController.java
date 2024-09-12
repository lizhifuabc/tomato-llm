package com.tomato.llm.examples.rag.controller;

import com.tomato.llm.examples.rag.domain.Answer;
import com.tomato.llm.examples.rag.domain.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 问题控制器
 *
 * @author lizhifu
 * @since 2024/9/11
 */
@RestController
@RequestMapping("/ask")
public class AskController {
    private final ChatClient aiClient;
    private final VectorStore vectorStore;

    @Value("classpath:/rag-prompt-template.st")
    private Resource ragPromptTemplate;


    public AskController(ChatClient aiClient, VectorStore vectorStore) {
        this.aiClient = aiClient;
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public Answer ask(@RequestBody Question question) {
        List<Document> similarDocuments = vectorStore
                .similaritySearch(SearchRequest.query(question.question())
                        .withTopK(2));
        List<String> contentList = similarDocuments.stream()
                .map(Document::getContent)
                .toList();

        String answer = aiClient.prompt()
                .user(userSpec -> userSpec
                        .text(ragPromptTemplate)
                        .param("input", question.question())
                        .param("documents", String.join("\n", contentList)))
                .call()
                .content();

        return new Answer(answer);
    }
}
