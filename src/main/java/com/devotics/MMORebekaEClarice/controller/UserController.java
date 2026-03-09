package com.devotics.MMORebekaEClarice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import com.devotics.MMORebekaEClarice.entity.User;
import com.devotics.MMORebekaEClarice.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {
        return userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();
    }
}