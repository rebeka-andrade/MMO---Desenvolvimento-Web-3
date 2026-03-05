package com.devotics.MMORebekaEClarice.services;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.devotics.MMORebekaEClarice.entities.Like;
import com.devotics.MMORebekaEClarice.entities.Post;
import com.devotics.MMORebekaEClarice.repositories.LikeRepository;
import com.devotics.MMORebekaEClarice.repositories.PostRepository;
import com.devotics.MMORebekaEClarice.repositories.UserRepository;
import com.devotics.MMORebekaEClarice.security.JwtUtils;

@Component
public class LikeService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private LikeRepository likeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    public ResponseEntity<?> likePost(Long postId) {

        String email = jwtUtils.getAuthorizedId();

        User user = (User) userRepo.findByEmail(email).get();
        Post post = postRepo.findById(postId).get();

        Like like = new Like();
        like.setCharacter(user);
        like.setPost(post);

        likeRepo.save(like);

        return ResponseEntity.ok().build();
    }
}