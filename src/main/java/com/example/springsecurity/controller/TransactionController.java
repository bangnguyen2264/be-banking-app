package com.example.springsecurity.controller;

import com.example.springsecurity.model.dto.AccountDto;
import com.example.springsecurity.model.form.DepositForm;
import com.example.springsecurity.model.form.TransferForm;
import com.example.springsecurity.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<AccountDto> findByAccountNumber(@RequestParam String accountNumber){
        return ResponseEntity.ok(transactionService.findByAccountNumber(accountNumber));
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
