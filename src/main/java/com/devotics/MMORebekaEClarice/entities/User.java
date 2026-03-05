package com.devotics.MMORebekaEClarice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate birthDate;
    private String profileImage;

    @OneToMany(mappedBy = "user")
    private List<GameCharacter> characters;
}