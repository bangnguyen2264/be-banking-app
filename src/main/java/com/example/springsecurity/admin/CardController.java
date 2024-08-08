package com.example.springsecurity.admin;

import com.example.springsecurity.model.dto.CardDto;
import com.example.springsecurity.service.impl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/card")
@RequiredArgsConstructor
public class CardController {
    private final CardServiceImpl cardService;
    @GetMapping
    public List<CardDto> getAllCard() {
        return cardService.getAllCards();
    }

    @PostMapping
    public ResponseEntity<String> createCard(@RequestParam int amount) {
        return ResponseEntity.ok(cardService.createCard(amount));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.ok("Delete card successfully.");
    }
}
