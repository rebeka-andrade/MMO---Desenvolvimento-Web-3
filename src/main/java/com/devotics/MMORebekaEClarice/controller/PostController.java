package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entity.Post;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.repository.CharacterRepository;
import com.devotics.MMORebekaEClarice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CharacterRepository characterRepository;

    public PostController(PostService postService, CharacterRepository characterRepository) {
        this.postService = postService;
        this.characterRepository = characterRepository;
    }

    @PostMapping("/{characterId}")
    public Post createPost(@PathVariable Long characterId, @RequestBody Post post) {
        Character character = characterRepository.findById(characterId).orElseThrow();
        return postService.createPost(post, character);
    }

    @GetMapping("/{characterId}")
    public List<Post> getPostsByCharacter(@PathVariable Long characterId) {
        Character character = characterRepository.findById(characterId).orElseThrow();
        return postService.getPostsByCharacter(character);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}