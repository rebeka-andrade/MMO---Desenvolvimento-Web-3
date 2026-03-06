package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entity.Live;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.repository.CharacterRepository;
import com.devotics.MMORebekaEClarice.service.LiveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lives")
public class LiveController {

    private final LiveService liveService;
    private final CharacterRepository characterRepository;

    public LiveController(LiveService liveService, CharacterRepository characterRepository) {
        this.liveService = liveService;
        this.characterRepository = characterRepository;
    }

    @PostMapping("/start/{characterId}")
    public Live startLive(@PathVariable Long characterId, @RequestParam String title) {
        Character character = characterRepository.findById(characterId).orElseThrow();
        return liveService.startLive(title, character);
    }

    @PostMapping("/end/{id}")
    public Live endLive(@PathVariable Long id) {
        return liveService.endLive(id);
    }

    @GetMapping("/active")
    public List<Live> getActiveLives() {
        return liveService.getActiveLives();
    }
}