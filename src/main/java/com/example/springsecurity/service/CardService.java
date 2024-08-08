package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.CardDto;
import com.example.springsecurity.model.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CardService {
    String createCard(int amount);
    List<CardDto> getAllCards();
    void deleteCard(Long cardId);

}
