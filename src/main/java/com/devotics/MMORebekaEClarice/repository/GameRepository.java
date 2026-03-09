package com.devotics.MMORebekaEClarice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devotics.MMORebekaEClarice.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}