package micronaut.kafka.poc;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

    @PostConstruct
    public void postConstruct() {
        LOG.info("started");
    }

    @Topic("messages")
    public void receive(@KafkaKey String messageId, String message) {
        LOG.info("[" + messageId + "] Received message: [" + message + "]");
    }
}
