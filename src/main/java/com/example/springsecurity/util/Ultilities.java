package com.example.springsecurity.util;

import com.example.springsecurity.model.entity.User;

import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@Slf4j
public class Ultilities {


    public static String getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null)
            log.info("get connectedUser: {}", auth.getName());
            return auth.getName();
    }
}
