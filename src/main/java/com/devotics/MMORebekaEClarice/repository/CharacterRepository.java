package com.devotics.MMORebekaEClarice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.entity.User;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUser(User user);
}