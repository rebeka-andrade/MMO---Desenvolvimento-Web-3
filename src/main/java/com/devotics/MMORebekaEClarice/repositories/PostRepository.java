package com.devotics.MMORebekaEClarice.repositories;

import com.devotics.MMORebekaEClarice.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCharacterId(Long characterId);

    @Query("""
            SELECT p FROM Post p
            WHERE p.character.id IN (
                SELECT f.following.id FROM Follow f
                WHERE f.follower.id = :characterId
            )
            ORDER BY p.createdAt DESC
            """)
    List<Post> feed(Long characterId);
}