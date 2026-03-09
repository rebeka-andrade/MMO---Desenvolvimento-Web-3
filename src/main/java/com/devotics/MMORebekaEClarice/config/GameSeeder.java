package com.devotics.MMORebekaEClarice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.devotics.MMORebekaEClarice.entity.Game;
import com.devotics.MMORebekaEClarice.repository.GameRepository;

@Configuration
public class GameSeeder {

    @Bean
    CommandLineRunner seedGames(GameRepository gameRepository) {
        return args -> {

            if(gameRepository.count() == 0){

                Game g1 = new Game();
                g1.setName("World of Warcraft");

                Game g2 = new Game();
                g2.setName("Final Fantasy XIV");

                Game g3 = new Game();
                g3.setName("Guild Wars 2");

                gameRepository.save(g1);
                gameRepository.save(g2);
                gameRepository.save(g3);
            }

        };
    }
}