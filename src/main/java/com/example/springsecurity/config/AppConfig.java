package com.example.springsecurity.config;

import com.example.springsecurity.model.entity.Account;
import com.example.springsecurity.model.entity.Card;
import com.example.springsecurity.model.entity.Role;
import com.example.springsecurity.model.entity.User;
import com.example.springsecurity.repository.AccountRepository;
import com.example.springsecurity.repository.CardRepository;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.util.Ultilities;
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

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            CardRepository cardRepository,
            PasswordEncoder passwordEncoder
) {
        return args -> {
            Role user = Role.builder()
                    .name("ROLE_USER")
                    .build();
            Role admin = Role.builder()
                    .name("ROLE_ADMIN")
                    .build();

            roleRepository.save(user);
            roleRepository.save(admin);

            User demoUser = User.builder()
                    .fullName("Admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(admin)
                    .phone("0123456789")
                    .address("HCM")
                    .build();
            userRepository.save(demoUser);

            log.info("Admin created");
                Card card = Card.builder()
                        .seriNumber("testcard")
                        .amount(100000)
                        .available(true)
                        .build();
                cardRepository.save(card);


        };
    }

}
