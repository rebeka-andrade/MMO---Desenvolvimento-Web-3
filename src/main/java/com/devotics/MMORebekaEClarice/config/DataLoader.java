package com.devotics.MMORebekaEClarice.config;

import com.devotics.MMORebekaEClarice.entities.Game;
import com.devotics.MMORebekaEClarice.repositories.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadGames(GameRepository gameRepository) {
        return args -> {

            if (gameRepository.count() == 0) {

                gameRepository.save(new Game(null, "League of Legends", "MOBA", null));
                gameRepository.save(new Game(null, "World of Warcraft", "MMORPG", null));
                gameRepository.save(new Game(null, "Fortnite", "Battle Royale", null));
                gameRepository.save(new Game(null, "Genshin Impact", "RPG", null));
                gameRepository.save(new Game(null, "Valorant", "FPS", null));

            }

        };
    }
}