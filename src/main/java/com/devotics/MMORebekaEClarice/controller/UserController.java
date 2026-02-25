package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entities.User;
import com.devotics.MMORebekaEClarice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        return userRepository.save(user);
    }
}