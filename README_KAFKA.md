# Tethys Kafka Integration

This project demonstrates how to integrate Apache Kafka with Spring Boot for sending and receiving messages.

## Components

### KafkaProducerService
A Spring-managed bean for sending messages to Kafka topics.

- `sendMessage(String topic, String message)`: Sends a message to the specified topic.
- `sendMessage(String topic, String key, String message)`: Sends a message with a key to the specified topic.

### ScheduledKafkaTask
A Spring-managed bean that sends scheduled messages to Kafka using Spring's `@Scheduled` annotation.

- Sends a message to "scheduled-topic" every 5 seconds with a timestamp.

### KafkaConfig
Configuration class that enables Kafka support in the Spring application.

### KafkaDemoRunner
A demo component that sends test messages when the application starts.

## Configuration

Kafka settings can be configured in `src/main/resources/application.properties`:

```properties
# Kafka Configuration
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=tethys-group
spring.kafka.consumer.auto-offset-reset=earliest
```

## Usage

1. Ensure Kafka is running on `localhost:9092` (or update the configuration accordingly).

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. The application will automatically send test messages and the consumer will print them.

4. Additionally, the application will send scheduled messages to "scheduled-topic" every 5 seconds.

## Dependencies

- Spring Boot Starter
- Spring Kafka

## Running Kafka

To run Kafka locally for testing:

1. Download and start ZooKeeper.
2. Download and start Kafka server.
3. Create a topic named "test-topic":
   ```bash
   kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
   ```