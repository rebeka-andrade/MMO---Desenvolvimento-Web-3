package com.devotics.MMORebekaEClarice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.devotics.MMORebekaEClarice.entity.Game;
import com.devotics.MMORebekaEClarice.repository.GameRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadGames(GameRepository repo) {
        return args -> {

            if(repo.count() == 0){

                Game g1 = new Game();
                g1.setName("World of Warcraft");

                Game g2 = new Game();
                g2.setName("League of Legends");

                Game g3 = new Game();
                g3.setName("Lost Ark");

                repo.save(g1);
                repo.save(g2);
                repo.save(g3);
            }
        };
    }
}