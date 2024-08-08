package com.example.springsecurity.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String accountNumber;
    private int balance;
    @OneToOne(mappedBy = "account")
    private User user;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

}
