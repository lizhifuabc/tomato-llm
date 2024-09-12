# Ollama

- 直接使用 `ChatModel` 来与 Ollama 交互

- 构建更高级的 LLM 工作流：`ChatClient`

  `ChatClient.Builder` 对象会自动配置，用于构建 `ChatClient` 实例。这个 `ChatClient` 是基于 `ChatModel` 的抽象，负责处理模型的底层交互。因此，`ChatClient` 可以更方便地集成各种复杂的逻辑，比如多步对话流程、上下文管理等。

## 接口地址

http://127.0.0.1:8080/chat

http://127.0.0.1:8080/chat/stream
