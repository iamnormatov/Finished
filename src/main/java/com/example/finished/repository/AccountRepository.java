package com.example.finished.repository;

import com.example.finished.dto.AccountDto;
import com.example.finished.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountIdAndDeletedAtIsNull(Integer entityId);
}
