package com.devotics.MMORebekaEClarice.repositories;

import com.devcaotics.mmo.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}