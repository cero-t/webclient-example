package ninja.cero.ecommerce.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    static List<Item> items;

    @GetMapping
    public Flux<Item> findAll() {
        return Flux.fromIterable(items);
    }

    static {
        items = new ArrayList<>();

        Item item1 = new Item();
        item1.id = 1L;
        item1.name = "test1";
        items.add(item1);

        Item item2 = new Item();
        item2.id = 2L;
        item2.name = "test2";
        items.add(item2);

        Item item3 = new Item();
        item3.id = 3L;
        item3.name = "test3";
        items.add(item3);
    }
}
