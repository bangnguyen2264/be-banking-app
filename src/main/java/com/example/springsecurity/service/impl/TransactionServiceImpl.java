package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.dto.TransactionDto;
import com.example.springsecurity.model.entity.Account;
import com.example.springsecurity.model.entity.Card;
import com.example.springsecurity.model.entity.Transaction;
import com.example.springsecurity.model.entity.User;
import com.example.springsecurity.model.form.DepositForm;
import com.example.springsecurity.model.form.TransferForm;
import com.example.springsecurity.repository.AccountRepository;
import com.example.springsecurity.repository.CardRepository;
import com.example.springsecurity.repository.TransactionRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.TransactionService;
import com.example.springsecurity.util.Ultilities;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    @Override
    public List<TransactionDto> getTransactions() {
            User currentUser = userRepository.findByEmail(Ultilities.getMe())
                    .orElseThrow();
            log.info("Current user: {}", currentUser.getUsername());
            List<Transaction> transaction = currentUser.getAccount().getTransactions();
            log.info("Transactions: {}", transaction);
            List<TransactionDto> transactionDtoList = transaction.stream()
                    .map(TransactionDto::toDto)
                    .toList();
            log.info("TransactionDtoList: {}", transactionDtoList.stream().toList());
            return transactionDtoList;
    }

    @Override
    public String transferMoney(TransferForm transferForm) {
        if(checkCurrentUser(transferForm.getFromAccount())  ){
            Account senderAccount = accountRepository.findByAccountNumber(transferForm.getFromAccount()).orElseThrow();
            Account receiveAccount = accountRepository.findByAccountNumber(transferForm.getToAccount()).orElseThrow();
            if (checkValidAmount(transferForm.getFromAccount(), transferForm.getAmount())) {
                senderAccount.setBalance(senderAccount.getBalance()-transferForm.getAmount());
                receiveAccount.setBalance(receiveAccount.getBalance()+transferForm.getAmount());
                accountRepository.saveAll(List.of(receiveAccount,senderAccount));
                buildTransferForm(transferForm,senderAccount,receiveAccount);
                return "Transfer successful";
            }
            else {
                throw new RuntimeException("User with account number " + transferForm.getFromAccount() + " has insufficient balance");
            }
        }
        else {
            throw new RuntimeException("User with account number " + transferForm.getFromAccount() + " does not exist");
        }
    }

    @Override
    public String deposit(DepositForm depositForm) {
        User currentUser = userRepository.findByEmail(Ultilities.getMe())
                .orElseThrow();
        Card card = cardRepository.findBySeriNumber(depositForm.getSeriNumber())
                .orElseThrow(()->new RuntimeException("Invalid seri number"));
        if(card.isAvailable())
        {
        currentUser.getAccount().setBalance(currentUser.getAccount().getBalance()+card.getAmount());

        card.setAvailable(false);
        Transaction transaction = Transaction.builder()
                .account(currentUser.getAccount())
                .transactionType("DEPOSIT")
                .amount(card.getAmount())
                .description("Deposit transaction")
                .build();
        currentUser.getAccount().getTransactions().add(transaction);
        transactionRepository.save(transaction);
        userRepository.save(currentUser);
        cardRepository.save(card);
        return "Deposit successful";
        }
        throw new RuntimeException("Invalid seri number");
    }

    private boolean checkCurrentUser(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        User currentUser = userRepository.findByEmail(Ultilities.getMe())
                .orElseThrow();
        return currentUser.equals(account.getUser());
    }
    private void buildTransferForm( TransferForm transferForm, Account senderAccount, Account receiverAccount) {
        Transaction senderForm = Transaction.builder()
                .fromAccount(transferForm.getFromAccount())
                .toAccount(transferForm.getToAccount())
                .amount(transferForm.getAmount())
                .transactionType("TRANSFER")
                .description(transferForm.getDescription())
                .account(senderAccount)
                .build();
        Transaction receiveForm = Transaction.builder()
                .fromAccount(transferForm.getFromAccount())
                .toAccount(transferForm.getToAccount())
                .amount(transferForm.getAmount())
                .transactionType("RECEIVE")
                .description(transferForm.getDescription())
                .account(receiverAccount)
                .build();
        senderAccount.getTransactions().add(senderForm);
        receiverAccount.getTransactions().add(receiveForm);
        accountRepository.saveAll(List.of(senderAccount,receiverAccount));
        transactionRepository.saveAll(List.of(senderForm, receiveForm));
    }
    private boolean checkValidAmount(String accountNumber, int amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        return amount <= account.getBalance();
    }

}
