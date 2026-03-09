package com.devotics.MMORebekaEClarice.service;

import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.entity.User;
import com.devotics.MMORebekaEClarice.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getFollowing(Long id) {
        Character c = characterRepository.findById(id).orElseThrow();
        return c.getFollowing();
    }

    public Character createCharacter(Character character, User user) {
        character.setUser(user);
        return characterRepository.save(character);
    }

    public List<Character> getCharactersByUser(User user) {
        return characterRepository.findByUser(user);
    }

    public Character getCharacterById(Long id) {
        return characterRepository.findById(id).orElseThrow();
    }

    public Character updateCharacter(Long id, Character updatedCharacter) {
        Character character = getCharacterById(id);
        character.setName(updatedCharacter.getName());
        character.setRole(updatedCharacter.getRole());
        character.setImageUrl(updatedCharacter.getImageUrl());
        character.setGame(updatedCharacter.getGame());
        return characterRepository.save(character);
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    public List<Character> searchCharacters(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }

    public void followCharacter(Long followerId, Long targetCharacterId) {
        Character follower = characterRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));
        Character target = characterRepository.findById(targetCharacterId)
                .orElseThrow(() -> new RuntimeException("Target not found"));

        if(!follower.getFollowing().contains(target)) {
            follower.getFollowing().add(target);
            characterRepository.save(follower);
        }
    }

}