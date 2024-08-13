package com.example.springsecurity.controller;

import com.example.springsecurity.model.dto.AccountDto;
import com.example.springsecurity.model.dto.TransactionDto;
import com.example.springsecurity.model.form.DepositForm;
import com.example.springsecurity.model.form.TransferForm;
import com.example.springsecurity.service.TransactionService;
import com.example.springsecurity.service.impl.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    @GetMapping
    public List<TransactionDto> getTransactions() {
        return transactionService.getTransactions();
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferForm transferForm){
        return ResponseEntity.ok(transactionService.transferMoney(transferForm));
    }
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositForm depositForm){
        return ResponseEntity.ok(transactionService.deposit(depositForm));
    }

}
