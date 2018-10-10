package micronaut.kafka.poc.support;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/ping")
public class PingController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }
}
