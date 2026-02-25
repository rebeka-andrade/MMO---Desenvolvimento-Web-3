package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.Game;
import com.devotics.MMORebekaEClarice.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @GetMapping
    public List<Game> listGames() {
        return gameRepository.findAll();
    }
}