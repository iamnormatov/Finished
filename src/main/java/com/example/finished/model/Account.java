package com.example.finished.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    @Column(name = "user_id")
    private Integer userId;
    private Float amount;
    private Integer currencyId;



//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "currencyId", referencedColumnName = "userId", insertable = false, updatable = false)
//    private Currency currency;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
