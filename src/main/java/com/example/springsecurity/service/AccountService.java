package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.AccountDto;

public interface AccountService {
    AccountDto findByAccountNumber(String accountNumber);

}
