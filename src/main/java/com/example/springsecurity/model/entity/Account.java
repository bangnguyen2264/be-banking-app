package com.example.springsecurity.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
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
    @JsonManagedReference
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    private List<Transaction> transactions;

}
