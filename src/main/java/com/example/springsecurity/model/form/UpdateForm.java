package com.example.springsecurity.model.form;

import com.example.springsecurity.model.entity.User;
import lombok.Data;

@Data
public class UpdateForm {
    private String fullName;
    private String email;
    private String phone;
    private String address;
}
