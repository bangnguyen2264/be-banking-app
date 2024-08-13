package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.core.util.Json;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {
    private Long id;
    private int amount;
    private String description;
    private String transactionType;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime createAt;

    public static TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .transactionType(transaction.getTransactionType())
                .createAt(transaction.getCreatedAt())
                .build();
    }
}
