package com.example.springsecurity.util;

import com.example.springsecurity.model.entity.User;

import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.security.SecureRandom;

@Slf4j
public class Ultilities {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null)
            log.info("get connectedUser: {}", auth.getName());
            return auth != null ? auth.getName() : null;
    }
    public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomDigit = secureRandom.nextInt(10);
            sb.append(randomDigit);
        }
        return sb.toString();
    }
    public static String generateSeriNumber() {
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            int randomDigit = secureRandom.nextInt(16);
            sb.append(randomDigit);
        }
        return sb.toString();
    }

}
