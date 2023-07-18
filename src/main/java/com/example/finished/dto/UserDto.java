package com.example.finished.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private Integer age;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;
    private Set<AccountDto> account;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
