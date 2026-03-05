package com.devotics.MMORebekaEClarice.dto;

import com.devotics.MMORebekaEClarice.entities.GameCharacter;   

public class PublicCharacterDTO {

    private String name;
    private int level;
    private String game;

    public PublicCharacterDTO(GameCharacter c) {
        this.name = c.getNickname();
        this.level = c.getLevel();
        this.game = c.getGame().getName();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getGame() {
        return game;
    }
}