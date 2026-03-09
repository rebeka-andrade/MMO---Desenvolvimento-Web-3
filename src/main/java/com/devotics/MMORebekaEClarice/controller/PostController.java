package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entity.Post;
import com.devotics.MMORebekaEClarice.entity.User;
import com.devotics.MMORebekaEClarice.dto.PostDTO;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.repository.CharacterRepository;
import com.devotics.MMORebekaEClarice.repository.UserRepository;
import com.devotics.MMORebekaEClarice.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    private final CharacterRepository characterRepository;

    public PostController(PostService postService, CharacterRepository characterRepository) {
        this.postService = postService;
        this.characterRepository = characterRepository;
    }

    @PostMapping("/{characterId}")
    public PostDTO createPostForCharacter(@PathVariable Long characterId, @RequestBody Post post) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        Post savedPost = postService.createPost(post, character);
        return new PostDTO(savedPost);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody Post post, Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Post savedPost = postService.savePost(post, user);
        return new PostDTO(savedPost);
    }

    @GetMapping("/feed")
    public List<PostDTO> getFeed(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Post> posts = postService.getFeedForUser(user);
        return posts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/feed/{characterId}")
    public List<PostDTO> getFeedByCharacter(@PathVariable Long characterId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        List<Post> posts = postService.getPostsByCharacter(character);
        return posts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return posts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    
    @GetMapping("/{characterId}")
    public List<PostDTO> getPostsByCharacter(@PathVariable Long characterId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        List<Post> posts = postService.getPostsByCharacter(character);
        return posts.stream().map(PostDTO::new).collect(Collectors.toList());
    }
}