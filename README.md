### Setup Kafka

https://docs.confluent.io/current/installation/docker/docs/installation/single-node-client.html

Create a docker network to enable DNS resolution across containers
```
docker network create kafka
```

Start zookeeper
```
docker run -d --net=kafka --name=zookeeper -e ZOOKEEPER_CLIENT_PORT=2181 confluentinc/cp-zookeeper:5.0.0
```

Start kafka
```
docker run -d -p 9092:9092 --net=kafka --name=kafka -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka:5.0.0
```

Create the `messages` topic
```
docker run --net=kafka --rm confluentinc/cp-kafka:5.0.0 kafka-topics --create --topic messages --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181
```

### Running the Application

Build and run the application in a docker container
```
./gradlew clean build
docker build -t micronaut-kafka-poc .
docker run --net=kafka -d -p 8080:8080 -t micronaut-kafka-poc
```

Produce a new message
```
curl -H "Content-Type: text/plain" -d "your message" http://localhost:8080/messages
```

You will see the message logged from the message listener in the application logs
```
21:02:12.655 [pool-1-thread-1] INFO  m.kafka.poc.producer.MessageListener - [b8c2900d-0bc4-4e82-aebc-120f8d076e65] Received message: [your message]
```