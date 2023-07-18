package com.example.finished.repository;

import com.example.finished.dto.UserDto;
import com.example.finished.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserIdAndDeletedAtIsNull(Integer entityId);

    public boolean existsByEmail(String email);
}
