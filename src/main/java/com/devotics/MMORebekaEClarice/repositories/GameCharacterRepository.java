package com.devotics.MMORebekaEClarice.repositories;

import com.devotics.MMORebekaEClarice.entities.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameCharacterRepository extends JpaRepository<GameCharacter, Long> {

    List<GameCharacter> findByUserId(Long userId);

    List<GameCharacter> findByGameId(Long gameId);

    List<GameCharacter> findByNicknameContainingIgnoreCase(String nickname);
}