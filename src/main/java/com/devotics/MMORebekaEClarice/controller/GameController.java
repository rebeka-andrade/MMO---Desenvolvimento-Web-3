package com.devotics.MMORebekaEClarice.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devotics.MMORebekaEClarice.entity.Game;
import com.devotics.MMORebekaEClarice.repository.GameRepository;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<Game> list(){
        return gameRepository.findAll();
    }
}