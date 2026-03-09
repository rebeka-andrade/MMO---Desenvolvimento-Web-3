package com.devotics.MMORebekaEClarice.repository;

import com.devotics.MMORebekaEClarice.entity.Live;
import com.devotics.MMORebekaEClarice.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LiveRepository extends JpaRepository<Live, Long> {
    List<Live> findByActiveTrue();
    List<Live> findByCharacterInAndActiveTrue(List<Character> characters);
}