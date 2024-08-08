package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.AccountDto;
import com.example.springsecurity.model.form.DepositForm;
import com.example.springsecurity.model.form.TransferForm;

public interface TransactionService {
     AccountDto findByAccountNumber(String accountNumber);
     String transferMoney(TransferForm transferForm);
     String deposit(DepositForm depositForm);
}
