package com.tomato.llm.examples.ollama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ChatController {
    private final ChatClient chatClient;

    ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * 聊天
     * @param question 问题
     * @return 回答
     */
    @GetMapping("/chat")
    String chat(@RequestParam(defaultValue = "唐诗三百首") String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

    /**
     * 聊天,自定义参数
     * @param question 问题
     * @return 回答
     */
    @GetMapping("/chat/generic-options")
    String chatWithGenericOptions(@RequestParam(defaultValue = "唐诗三百首") String question) {
        return chatClient.prompt()
                .user(question)
                .options(ChatOptionsBuilder.builder()
                        // 低温度（接近 0）：模型会更倾向于生成更确定、更常见的词和短语，结果更保守和确定性强。适合生成更可靠的答案，但缺乏多样性。
                        //  温度 1.0：生成过程是平衡的，既有一定的随机性，也保持合理的生成质量。
                        //  高温度（大于 1）：增加生成的随机性，模型更可能生成不常见或更具创造性的词汇，但生成结果可能会变得不太一致或不符合上下文。

                        // 0.7 是一个较为适中的温度，既能保持生成质量，又有一定的创造性和随机性。
                        .withTemperature(0.7f)
                        .build())
                .call()
                .content();
    }

    /**
     * 聊天,自定义参数
     * @param question 问题
     * @return 回答
     */
    @GetMapping("/chat/provider-options")
    String chatWithProviderOptions(@RequestParam(defaultValue = "唐诗三百首") String question) {
        return chatClient.prompt()
                .user(question)
                .options(OllamaOptions.create()
                        // 模型
                        .withModel("qwen2_1.5B:latest")
                        // 设置重复惩罚系数（Repeat Penalty）是为了减少生成文本中重复词或短语的出现。具体来说，
                        // 重复惩罚系数通过惩罚模型生成的重复内容，使模型更倾向于生成多样化的回答。如果惩罚系数较高，模型会更避免重复生成相同的词汇或短语。

                        // 低惩罚系数（<1）：模型可能会重复生成内容，尤其是当上下文较短时。
                        // 默认值（1.0）：模型会自然生成文本，不特别惩罚或鼓励重复。
                        // 高惩罚系数（>1）：模型会更积极地避免重复，但如果设置过高，可能会影响生成质量，使生成的内容显得不自然。

                        // 1.5f：重复惩罚系数为1.5，这意味着模型在生成文本时会更倾向于避免重复。
                        .withRepeatPenalty(1.5f))
                .call()
                .content();
    }


    /**
     * 流式聊天 注意，如果使用浏览器直接访问，会出现乱码
     * @param question 问题
     * @return 回答
     */
    @GetMapping("/chat/stream")
    Flux<String> chatStream(@RequestParam(defaultValue = "你是谁") String question) {
        return chatClient.prompt()
                .user(question)
                .stream()
                .content();
    }
}
