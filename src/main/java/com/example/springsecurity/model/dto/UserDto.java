package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String phone;
    private String address;
    private AccountDto account;

    public static UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .dob(user.getDob())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .account(AccountDto.toDtoFromUser(user))
                .build();
    }
}
