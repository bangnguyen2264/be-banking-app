package com.example.springsecurity.repository;

import com.example.springsecurity.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {
    Optional<Card> findBySeriNumber(String seriNumber);
    Card deleteCardByAvailableFalse();
}
