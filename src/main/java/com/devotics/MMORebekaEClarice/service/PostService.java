package com.devotics.MMORebekaEClarice.service;

import com.devotics.MMORebekaEClarice.entity.Post;
import com.devotics.MMORebekaEClarice.entity.Character;
import com.devotics.MMORebekaEClarice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
}