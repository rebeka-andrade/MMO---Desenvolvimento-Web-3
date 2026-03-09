package com.devotics.MMORebekaEClarice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lives")
public class Live {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean active;
    private LocalDateTime startedAt;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    @PrePersist
    public void prePersist() {
        startedAt = LocalDateTime.now();
        active = true;
    }
}