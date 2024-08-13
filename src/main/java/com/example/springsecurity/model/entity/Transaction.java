package com.example.springsecurity.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "accountId")
    @JsonBackReference
    private Account account;
    private String fromAccount;
    private String toAccount;
    private int amount;
    private String description;
    private String transactionType;

}
