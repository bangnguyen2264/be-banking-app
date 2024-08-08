package com.example.springsecurity.model.form;

import lombok.Data;

@Data
public class TransferForm {
    private String fromAccount;
    private String toAccount;
    private int amount;
    private String description;
}
