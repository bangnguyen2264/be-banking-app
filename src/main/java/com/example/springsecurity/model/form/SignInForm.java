package com.example.springsecurity.model.form;

import lombok.Data;

@Data
public class SignInForm {
    private String email;
    private String password;
}
