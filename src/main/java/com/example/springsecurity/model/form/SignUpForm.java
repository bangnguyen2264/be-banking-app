package com.example.springsecurity.model.form;

import com.example.springsecurity.model.entity.User;
import lombok.Data;

@Data
public class SignUpForm {
    private String firstname;
    private String lastname;
    private String userName;
    private String password;

}
