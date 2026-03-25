package src.gamelogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GameLogBackendApplication {

    static void main(String[] args) {
        SpringApplication.run(GameLogBackendApplication.class, args);
    }

}
