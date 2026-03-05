package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.Live;
import com.devotics.MMORebekaEClarice.repositories.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lives")
public class LiveController {

    @Autowired
    private LiveRepository repository;

    @PostMapping
    public Live create(@RequestBody Live live) {
        return repository.save(live);
    }

    @GetMapping("/active/{characterId}")
    public List<Live> activeLives(@PathVariable Long characterId) {
        return repository.findByCharacterIdAndActiveTrue(characterId);
    }
}