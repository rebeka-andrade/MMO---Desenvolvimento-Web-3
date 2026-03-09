package com.devotics.MMORebekaEClarice.repository;

import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUser(User user);
    List<Character> findByNameContainingIgnoreCase(String name);
    
}