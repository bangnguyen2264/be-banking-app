package com.example.springsecurity.config;

import com.example.springsecurity.model.entity.Card;
import com.example.springsecurity.model.entity.Role;
import com.example.springsecurity.model.entity.User;
import com.example.springsecurity.repository.CardRepository;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class AppConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initApp() {
        return args -> {
            if (!roleRepository.existsByName("ROLE_USER")) {
                roleRepository.save(Role.builder().id(1L).name("ROLE_USER").build());
            }
            if (!roleRepository.existsByName("ROLE_ADMIN")) {
                roleRepository.save(Role.builder().id(2L).name("ROLE_ADMIN").build());
            }
            log.info("Roles initialized successfully");

            Role userRole = roleRepository.findById(1L).orElseThrow();
            Role adminRole = roleRepository.findById(2L).orElseThrow();

            if (!userRepository.existsByEmail("admin@gmail.com")) {
                userRepository.save(
                        User.builder()
                                .id(1L)
                                .fullName("admin")
                                .email("admin@gmail.com")
                                .password(passwordEncoder.encode("admin"))
                                .build()
                );
            }
            log.info("Admin initialized successfully");

            if (!cardRepository.existsBySeriNumber("testcard")) {
                cardRepository.save(
                        Card.builder()
                                .seriNumber("testcard")
                                .amount(100000)
                                .available(true)
                                .build()
                );
            }
            log.info("Cards initialized successfully");
        };
    }
}
