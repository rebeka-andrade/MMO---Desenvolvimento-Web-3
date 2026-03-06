package com.devotics.MMORebekaEClarice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devotics.MMORebekaEClarice.entity.Live;
import java.util.List;

public interface LiveRepository extends JpaRepository<Live, Long> {
    List<Live> findByActiveTrue();
}