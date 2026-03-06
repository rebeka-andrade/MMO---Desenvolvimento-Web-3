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
}