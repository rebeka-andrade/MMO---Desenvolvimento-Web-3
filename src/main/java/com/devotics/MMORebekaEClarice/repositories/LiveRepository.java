package com.devotics.MMORebekaEClarice.repositories;

import com.devotics.MMORebekaEClarice.entities.Live;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveRepository extends JpaRepository<Live, Long> {

    List<Live> findByCharacterIdAndActiveTrue(Long characterId);
}