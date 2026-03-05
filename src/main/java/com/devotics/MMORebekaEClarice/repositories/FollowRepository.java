package com.devotics.MMORebekaEClarice.repositories;

import com.devotics.MMORebekaEClarice.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollowerId(Long followerId);

    List<Follow> findByFollowingId(Long followingId);

    long countByFollowingId(Long id);

    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
}