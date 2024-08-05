package com.example.springsecurity.util;

import com.example.springsecurity.model.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

public class Ultilities {

    public static User getMe(Principal connectedUser) {
        return (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    }
}
