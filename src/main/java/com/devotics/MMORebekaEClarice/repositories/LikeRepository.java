package com.devotics.MMORebekaEClarice.repositories;

import com.devotics.MMORebekaEClarice.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByCharacterIdAndPostId(Long characterId, Long postId);

    long countByPostId(Long postId);
}