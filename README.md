# tomato-llm
基于 spring ai https://spring.io/projects/spring-ai

## ollama

安装：

```bash
cd /opt
curl -fsSL https://ollama.com/install.sh | sh
ollama run qwen2
```

```bash
root@DESKTOP-4R5HHC9:/opt# ollama list
NAME            ID              SIZE    MODIFIED       
qwen2:latest    dd314f039b9d    4.4 GB  45 seconds ago
root@DESKTOP-4R5HHC9:/opt#
```

ollama安装后默认只能本地访问，需要配置远程访问api

```bash
#1.找到服务的单元文件：
#/etc/systemd/system/目录下
sudo vi /etc/systemd/system/ollama.service
 
#2.修改配置文件，分为如下2钟情况 
#情况1：添加环境变量：
[Service]
Environment="OLLAMA_HOST=0.0.0.0:11434"
#情况2：如果已经有
Environment="PATH=xxx:/root/bin" "OLLAMA_HOST=0.0.0.0:11434"
 
#3.为了使更改生效，您需要重新加载systemd的配置。使用以下命令：
sudo systemctl daemon-reload

#4.重启服务以应用更改：
sudo systemctl restart ollama
```

## 代码示例

[tomato-llm-examples](tomato-llm-examples)

- 向量数据库 ChromaDB：[tomato-llm-examples-chroma](tomato-llm-examples/tomato-llm-examples-chroma)
- ollama：[tomato-llm-examples-ollama](tomato-llm-examples/tomato-llm-examples-ollama)
