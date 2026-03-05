package com.devotics.MMORebekaEClarice.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.devotics.MMORebekaEClarice.dto.RegisterDTO;
import com.devotics.MMORebekaEClarice.dto.LoginDTO;
import com.devotics.MMORebekaEClarice.entities.User;
import com.devotics.MMORebekaEClarice.repositories.UserRepository;
import com.devotics.MMORebekaEClarice.security.JwtUtils;

@Component
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<?> register(RegisterDTO dto) {

        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já existe");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        userRepo.save(user);

        return ResponseEntity.ok("Usuário criado!");
    }

    public ResponseEntity<?> login(LoginDTO login) {

        Optional<User> userOpt = userRepo.findByEmail(login.getEmail());

        if (userOpt.isPresent()) {

            User user = userOpt.get();

            if (encoder.matches(login.getPassword(), user.getPassword())) {

                String token = jwtUtils.generateToken(user.getEmail(), "USER");

                return ResponseEntity.ok(
                        java.util.Map.of("token", token));
            }
        }

        return ResponseEntity.badRequest().body("Credenciais inválidas");
    }
}