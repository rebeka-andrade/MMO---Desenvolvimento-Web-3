package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.entity.User;
import com.devotics.MMORebekaEClarice.repository.UserRepository;
import com.devotics.MMORebekaEClarice.service.CharacterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final UserRepository userRepository;

    public CharacterController(CharacterService characterService, UserRepository userRepository) {
        this.characterService = characterService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Character createCharacter(@RequestBody Character character, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        return characterService.createCharacter(character, user);
    }

    @GetMapping
    public List<Character> getCharacters(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        return characterService.getCharactersByUser(user);
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    @PutMapping("/{id}")
    public Character updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        return characterService.updateCharacter(id, character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
    }

    @GetMapping("/search")
    public List<Character> search(@RequestParam String name) {
        return characterService.searchCharacters(name);
    }

    @GetMapping("/{id}/following")
    public List<Character> getFollowing(@PathVariable Long id) {
        return characterService.getFollowing(id);
    }

    @PostMapping("/follow/{targetId}")
    public ResponseEntity<?> followCharacter(Authentication authentication, @PathVariable Long targetId) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não logado");
        }

        User loggedUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Character> userCharacters = characterService.getCharactersByUser(loggedUser);
        if (userCharacters.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não possui personagem para seguir");
        }

        Character followerCharacter = userCharacters.get(0);

        try {
            characterService.followCharacter(followerCharacter.getId(), targetId);
            return ResponseEntity.ok("Agora você segue esse personagem");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}