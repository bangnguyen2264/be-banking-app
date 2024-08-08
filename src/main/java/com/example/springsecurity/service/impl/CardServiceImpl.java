package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.dto.CardDto;
import com.example.springsecurity.model.entity.Card;
import com.example.springsecurity.repository.CardRepository;
import com.example.springsecurity.service.CardService;
import com.example.springsecurity.util.Ultilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public String createCard(int amount) {
        Card card = Card.builder()
                .seriNumber(Ultilities.generateSeriNumber())
                .amount(amount)
                .available(true)
                .build();
        cardRepository.save(card);
        return "Card created";
    }

    @Override
    public List<CardDto> getAllCards() {
        return cardRepository.findAll().stream().map(CardDto::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCard(Long cardId) {
        cardRepository.deleteCardByAvailableFalse();
    }
}
