# 向量数据库

```bash
# 安装 ChromaDB
docker-compose up -d
```

## Chroma

Chroma 是开源的嵌入向量数据库。它提供了文档嵌入向量、内容和元数据存储，并配备了通过这些嵌入向量进行搜索的工具，包括元数据过滤。

## Spring Function Catalog

[spring-cloud/spring-functions-catalog: Reusable Spring Functions for data driven microservices (github.com)](https://github.com/spring-cloud/spring-functions-catalog/)

Spring Cloud Function 是一个框架，它将函数式编程模型引入了 Spring 应用程序，使你可以以函数（如 `Supplier`、`Function` 和 `Consumer`）的方式来编写代码，并将这些函数暴露为 HTTP、消息队列、或其他触发器来使用。Spring Function Catalog 是其中的一个核心概念，提供了一个 **函数注册表**，你可以动态查找、组合和调用不同的函数。

```yml
spring:
  cloud:
    function:
      definition: fileSupplier|documentReader|splitter|vectorStoreConsumer  # 定义函数链：文件供应器、文档阅读器、分割器、向量存储消费者
```

整个功能流程如下：

1. **`fileSupplier` 函数**：
   - 该函数由 Spring Function Catalog 提供，并在 `src/main/resources/application.yml` 中配置，监视 `/tmp/dropoff` 目录中的文件。
   - 当有新文件出现时，它会读取该文件的内容，将其作为 `Message<byte[]>` 包装在一个 `Flux` 中，并发送给 `documentReader` 函数。
2. **`documentReader` 函数**：
   - 该函数接收来自 `fileSupplier` 的 `Flux<Message<byte[]>>`，通过 `ByteArrayResource` 创建资源，进而使用 `TikaDocumentReader` 解析文件。
   - 它将文件解析为一个 `List<Document>`，并通过 `Flux<List<Document>>` 返回。
   - 同时，它会从 `Message` 头信息中获取源文件的名称，并将其作为 "source" 元数据添加到文档的元数据中。
3. **`splitter` 函数**：
   - 该函数会将文档拆分成多个较小的文档块，以便在启用 RAG（检索增强生成）的 Spring AI 应用程序中，避免整个文档需要作为上下文发送。
   - 分割后的 `List<Document>` 会被包装成一个新的 `Flux` 返回。
4. **`vectorStoreConsumer` 函数**：
   - 这是一个简单的包装器，围绕 Spring AI 的 `VectorStore`（在本例中是 `ChromaVectorStore`）进行封装。
   - 它通过 `doOnNext()` 从 `Flux` 中提取 `List<Document>`，并调用 `VectorStore` 的 `accept()` 方法将文档保存到向量存储中。
   - 由于到目前为止所有内容都是通过 `Flux` 传递的，而 `VectorStore` 仅接受 `List<Document>`，所以使用 `doOnNext()` 提取文档列表。
5. **`ApplicationRunner` 启动函数**：
   - 通过 `ApplicationRunner` 的 `bean` 来触发这个功能，它会查找组合的函数并调用其 `run()` 方法。
   - 一旦启动，`fileSupplier` 函数会持续监控 `/tmp/dropoff` 目录中的文件。
