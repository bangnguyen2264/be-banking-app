package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.Card;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDto {
    private String seriNumber;
    private int amount;
    private boolean available;

    public static CardDto toDto(Card card) {
        return CardDto.builder()
                .seriNumber(card.getSeriNumber())
                .amount(card.getAmount())
                .available(card.isAvailable())
                .build();
    }
}
