package com.example.finished.repository;

import com.example.finished.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findByCurrencyIdAndDeletedAtIsNull(Integer entityId);
}
