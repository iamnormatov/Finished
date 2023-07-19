package com.example.finished.model;

import com.example.finished.dto.AccountDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users_test")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private Integer age;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;



//    @OneToMany(mappedBy = "user_id", fetch = FetchType.EAGER)
//    private Set<Account> accounts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
