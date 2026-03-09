package com.devotics.MMORebekaEClarice.service;

import com.devotics.MMORebekaEClarice.entity.Post;
import com.devotics.MMORebekaEClarice.entity.User;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.repository.CharacterRepository;
import com.devotics.MMORebekaEClarice.repository.PostRepository;
import com.devotics.MMORebekaEClarice.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CharacterRepository characterRepository;

    private final CharacterService characterService;

    public PostService(PostRepository postRepository, CharacterService characterService) {
        this.postRepository = postRepository;
        this.characterService = characterService;
    }

    public Post createPost(Post post, Character character) {
        post.setCharacter(character);
        return postRepository.save(post);
    }

    public List<Post> getPostsByCharacter(Character character) {
        return postRepository.findByCharacter(character);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getFeed(Long characterId) {
        Character c = characterService.getCharacterById(characterId);
        List<Character> following = c.getFollowing();
        following.add(c);

        return postRepository.findByCharacterInOrderByCreatedAtDesc(following);
    }

    public Post savePost(Post post, User user) {
    
        Character character = characterRepository.findByUser(user).stream().findFirst().orElse(null);
        post.setCharacter(character);
        return postRepository.save(post);
    }

    public List<Post> getFeedForUser(User user) {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}