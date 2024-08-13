package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.AccountDto;
import com.example.springsecurity.model.dto.TransactionDto;
import com.example.springsecurity.model.form.DepositForm;
import com.example.springsecurity.model.form.TransferForm;

import java.util.List;

public interface TransactionService {
     List<TransactionDto> getTransactions();
     String transferMoney(TransferForm transferForm);
     String deposit(DepositForm depositForm);
}
