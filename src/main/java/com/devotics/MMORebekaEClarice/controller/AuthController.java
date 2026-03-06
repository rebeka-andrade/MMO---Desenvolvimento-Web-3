package com.devotics.MMORebekaEClarice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devotics.MMORebekaEClarice.dto.LoginDTO;
import com.devotics.MMORebekaEClarice.dto.RegisterDTO;
import com.devotics.MMORebekaEClarice.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        System.out.println("Tentando login com: " + dto.getEmail());
        return authService.login(dto);
    }

}