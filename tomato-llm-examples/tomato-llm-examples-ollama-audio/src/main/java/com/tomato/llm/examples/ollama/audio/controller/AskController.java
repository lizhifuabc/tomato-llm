package com.tomato.llm.examples.ollama.audio.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ask controller
 *
 * @author lizhifu
 * @since 2024/9/11
 */
@Controller
@RequestMapping("/ask")
public class AskController {
    /**
     * 提示词模板
     */
    @Value("classpath:prompt-template.st")
    private Resource promptTemplateResource;

    @GetMapping
    public String record() {
        return "askView";
    }
}
