package com.devotics.MMORebekaEClarice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String text;
    private String imageUrl; 

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "character_id")
    private Character character;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

     private LocalDateTime createdAt = LocalDateTime.now();
}