# tweet-to-ms

![Java](https://img.shields.io/badge/java-21-blue)
![Spring](https://img.shields.io/badge/spring--boot-brightgreen)
![Kafka](https://img.shields.io/badge/apache--kafka-black)

AI Generated Tweet → Kafka Microservice (Spring Boot + Spring AI + Ollama)

---

## 🚀 Overview

This project demonstrates a microservice architecture where:

- Spring AI + Ollama generate synthetic tweets
- Tweets are published to Apache Kafka
- Messages follow Twitter-like JSON structure
- Scheduler runs every 30 seconds

Main keywords:

- Java
- Spring
- Kafka
- Microservices
- Elasticsearch

---

## 🧱 Architecture

```
Scheduler
   |
   v
Spring AI (Ollama)
   |
   v
Kafka Producer
   |
   v
Kafka Topic
```

---

## 📦 Modules

```
tweet-to-ms
│
├── ai-generated-tweet-to-kafka-service
│   └── Tweet generator + Kafka producer
│
├── common-config
│   └── Shared config
│
├── kafka
│   └── Kafka setup
│
├── docker-compose
│   └── Kafka + Zookeeper
│
├── app-config-data
│
└── pom.xml (parent)
```

---

## 🔁 Tweet Format

```json
{
  "createdAt": "Mon Feb 02 14:12:03 UTC 2026",
  "id": 123456,
  "text": "Spring Boot microservices with Kafka make event-driven systems powerful.",
  "user": {
    "id": 999
  }
}
```

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring AI
- Apache Kafka
- Maven (multi-module)
- Ollama
- Docker Compose

---

## 🐳 Start Kafka

```bash
cd docker-compose
docker compose up -d
```

---

## 🤖 Start Ollama

```bash
brew install ollama
ollama pull llama3.1:8b
ollama run llama3.1:8b
```

---

## ▶️ Run Application

From root:

```bash
mvn clean install
```

Then:

```bash
cd ai-generated-tweet-to-kafka-service
mvn spring-boot:run
```

---

## 📤 Consume Kafka Messages

```bash
docker exec -it kafka kafka-console-consumer.sh \
--bootstrap-server localhost:9092 \
--topic tweet-topic \
--from-beginning
```

---

## ⚙️ Configuration

application.yml:

```yaml
tweet:
  scheduler:
    interval: 30000

spring:
  kafka:
    bootstrap-servers: localhost:9092
```

---

## 📄 Environment Variables

| Name | Description |
|------|-------------|
| KAFKA_BOOTSTRAP | Kafka broker |
| OLLAMA_HOST | Ollama host |
| TWEET_TOPIC | Kafka topic |

---

## 🧩 Roadmap

- Schema Registry
- Dead Letter Topic
- Retry
- Observability
- Kubernetes

---

## 👤 Author

Tathao Nguyen

---

## 📜 License

MIT
