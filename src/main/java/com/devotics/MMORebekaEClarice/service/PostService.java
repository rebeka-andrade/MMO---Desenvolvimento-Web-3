package com.devotics.MMORebekaEClarice.service;

import com.devotics.MMORebekaEClarice.entity.Post;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
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
}