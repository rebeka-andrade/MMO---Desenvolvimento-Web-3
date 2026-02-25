package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.Like;
import com.devotics.MMORebekaEClarice.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeRepository repository;

    @PostMapping
    public Like like(@RequestBody Like like) {
        return repository.save(like);
    }

    @GetMapping("/count/{postId}")
    public long count(@PathVariable Long postId) {
        return repository.countByPostId(postId);
    }
}