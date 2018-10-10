package micronaut.kafka.poc;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface MessageProducer {

    @Topic("messages")
    void sendMessage(@KafkaKey String messageId, String message);
}
