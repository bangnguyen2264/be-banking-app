package com.example.springsecurity.controller;

import com.example.springsecurity.model.dto.AccountDto;
import com.example.springsecurity.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @GetMapping
    public ResponseEntity<AccountDto> findByAccountNumber(@RequestParam String accountNumber){
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
    }
}
