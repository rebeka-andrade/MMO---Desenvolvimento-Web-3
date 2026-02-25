package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.Post;
import com.devotics.MMORebekaEClarice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository repository;

    @PostMapping
    public Post create(@RequestBody Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return repository.save(post);
    }

    @GetMapping("/character/{characterId}")
    public List<Post> findByCharacter(@PathVariable Long characterId) {
        return repository.findByCharacterId(characterId);
    }
}