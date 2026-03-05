package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.Follow;
import com.devotics.MMORebekaEClarice.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
public class FollowController {

    @Autowired
    private FollowRepository repository;

    @PostMapping
    public Follow follow(@RequestBody Follow follow) {
        return repository.save(follow);
    }

    @GetMapping("/followers/{characterId}")
    public long followers(@PathVariable Long characterId) {
        return repository.countByFollowingId(characterId);
    }
}