package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.Comment;
import com.devotics.MMORebekaEClarice.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        return repository.save(comment);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> findByPost(@PathVariable Long postId) {
        return repository.findByPostId(postId);
    }
}