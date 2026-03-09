package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.entity.User;
import com.devotics.MMORebekaEClarice.repository.UserRepository;
import com.devotics.MMORebekaEClarice.service.CharacterService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{followerId}/follow/{targetId}")
    public ResponseEntity<?> followCharacter(
            @PathVariable Long followerId,
            @PathVariable Long targetId) {

        characterService.followCharacter(followerId, targetId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/characters/follow/{targetId}")
    public ResponseEntity<?> followCharacter(
        @AuthenticationPrincipal Character loggedCharacter,
        @PathVariable Long targetId
    ) {
        characterService.followCharacter(loggedCharacter.getId(), targetId);
        return ResponseEntity.ok().build();
    }
}