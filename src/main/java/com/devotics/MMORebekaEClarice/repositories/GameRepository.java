package com.devotics.MMORebekaEClarice.repositories;

import com.devotics.MMORebekaEClarice.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}