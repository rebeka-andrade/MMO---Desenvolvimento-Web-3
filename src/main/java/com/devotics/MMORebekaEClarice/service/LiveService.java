package com.devotics.MMORebekaEClarice.service;

import com.devotics.MMORebekaEClarice.entity.Live;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.repository.LiveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveService {

    private final LiveRepository liveRepository;

    public LiveService(LiveRepository liveRepository) {
        this.liveRepository = liveRepository;
    }

    public Live startLive(String title, Character character) {
        Live live = new Live();
        live.setTitle(title);
        live.setCharacter(character);
        return liveRepository.save(live);
    }

    public Live endLive(Long id) {
        Live live = liveRepository.findById(id).orElseThrow();
        live.setActive(false);
        return liveRepository.save(live);
    }

    public List<Live> getActiveLives() {
        return liveRepository.findByActiveTrue();
    }
}