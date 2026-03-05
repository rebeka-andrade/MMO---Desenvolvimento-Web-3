package com.devotics.MMORebekaEClarice.services;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.devotics.MMORebekaEClarice.dto.NewPostDTO;
import com.devotics.MMORebekaEClarice.entities.GameCharacter;
import com.devotics.MMORebekaEClarice.entities.Post;
import com.devotics.MMORebekaEClarice.repositories.PostRepository;
import com.devotics.MMORebekaEClarice.repositories.UserRepository;
import com.devotics.MMORebekaEClarice.security.JwtUtils;

@Component
public class PostService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> create(NewPostDTO dto){

        String email = jwtUtils.getAuthorizedId();

        User user = (User) userRepo.findByEmail(email).get();

        Post post = new Post();
        post.setContent(dto.getContent());
        GameCharacter character = null;
        post.setCharacter(character);

        postRepo.save(post);

        return ResponseEntity.ok().build();
    }
}