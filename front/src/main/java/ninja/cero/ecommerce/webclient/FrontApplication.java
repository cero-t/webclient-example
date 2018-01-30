package ninja.cero.ecommerce.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@SpringBootApplication
@RestController
public class FrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }

    @Bean
    WebClient webClient() {
        return WebClient.builder().baseUrl("http://localhost:8081").build();
    }

    @Autowired
    WebClient webClient;

    @GetMapping("/")
    public Flux<Item> findFlux() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(Item.class);
    }

    @GetMapping("/block")
    public List<Item> findBlock() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(Item.class)
                .collectList()
                .block(Duration.ofSeconds(10L));
    }
}
