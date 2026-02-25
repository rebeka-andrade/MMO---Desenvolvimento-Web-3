package com.devotics.MMORebekaEClarice.repositories;

import com.devcaotics.mmo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCharacterId(Long characterId);
}