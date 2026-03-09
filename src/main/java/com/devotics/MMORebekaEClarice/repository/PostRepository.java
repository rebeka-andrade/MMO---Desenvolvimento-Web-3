package com.devotics.MMORebekaEClarice.repository;

import com.devotics.MMORebekaEClarice.entity.Post;
import com.devotics.MMORebekaEClarice.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCharacter(Character character);

    List<Post> findByCharacterInOrderByCreatedAtDesc(List<Character> characters);

    List<Post> findAllByOrderByCreatedAtDesc();
}