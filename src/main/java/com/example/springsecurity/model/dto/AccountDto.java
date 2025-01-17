package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.Account;
import com.example.springsecurity.model.entity.Transaction;
import com.example.springsecurity.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AccountDto {
    private Long id;
    private String accountNumber;
    private int balance;
    private Long userId;
    private List<Transaction> transactions;

    public static AccountDto toDtoFromUser(User user) {
        return AccountDto.builder()
                .id(user.getAccount().getId())
                .accountNumber(user.getAccount().getAccountNumber())
                .balance(user.getAccount().getBalance())
                .userId(user.getId())
                .build();
    }
    public static AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .userId(account.getUser().getId())
                .transactions(account.getTransactions())
                .build();
    }
}
