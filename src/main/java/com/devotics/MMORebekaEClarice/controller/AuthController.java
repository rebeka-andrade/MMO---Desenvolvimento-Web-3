package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entity.User;
import com.devotics.MMORebekaEClarice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}