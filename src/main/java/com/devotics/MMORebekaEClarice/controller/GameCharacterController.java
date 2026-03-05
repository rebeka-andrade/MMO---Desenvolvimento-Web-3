package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.GameCharacter;
import com.devotics.MMORebekaEClarice.repositories.GameCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class GameCharacterController {

    @Autowired
    private GameCharacterRepository repository;

    @PostMapping
    public GameCharacter create(@RequestBody GameCharacter character) {
        return repository.save(character);
    }

    @GetMapping("/user/{userId}")
    public List<GameCharacter> findByUser(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    @GetMapping("/search")
    public List<GameCharacter> search(@RequestParam String name) {
        return repository.findByNicknameContainingIgnoreCase(name);
    }

    @GetMapping("/{id}")
    public GameCharacter getCharacter(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
}