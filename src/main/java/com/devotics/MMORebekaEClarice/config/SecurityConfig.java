package com.devotics.MMORebekaEClarice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**",
                    "/login",
                    "/login.html",
                    "/register.html",
                    "/css/**",
                    "/js/**",
                    "/h2-console/**"
                ).permitAll()
                .anyRequest().authenticated()
            )

            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            )

            .formLogin(form -> form
                .loginPage("/index.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/gamer.html", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login.html")
            )

            .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}