package micronaut.kafka.poc;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.validation.constraints.Size;
import java.util.UUID;

@Controller("/messages")
public class MessageController {

    private final MessageProducer messageProducer;

    @Inject
    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }


    @Post(consumes = MediaType.TEXT_PLAIN)
    String create(@Size(max = 1024) @Body String message) {
        String messageId = UUID.randomUUID().toString();
        messageProducer.sendMessage(messageId, message);
        return messageId;
    }
}
