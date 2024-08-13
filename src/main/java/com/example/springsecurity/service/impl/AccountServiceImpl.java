package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.dto.AccountDto;
import com.example.springsecurity.model.entity.Account;
import com.example.springsecurity.repository.AccountRepository;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountDto findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()->new RuntimeException("Account not found"));
        return AccountDto.toDto(account);
    }
}
