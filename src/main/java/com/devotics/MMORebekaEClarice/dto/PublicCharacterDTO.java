package com.devcaotics.mmo.dto;

import com.devcaotics.mmo.entities.Character;

public class PublicCharacterDTO {

    private String name;
    private int level;
    private String game;

    public PublicCharacterDTO(Character c) {
        this.name = c.getName();
        this.level = c.getLevel();
        this.game = c.getGame().getTitle();
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