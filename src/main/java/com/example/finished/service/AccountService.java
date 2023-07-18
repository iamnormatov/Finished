package com.example.finished.service;

import com.example.finished.dto.AccountDto;
import com.example.finished.dto.ResponseDto;
import com.example.finished.dto.SimpleCRUD;
import com.example.finished.model.Account;
import com.example.finished.model.Currency;
import com.example.finished.model.User;
import com.example.finished.repository.AccountRepository;
import com.example.finished.repository.CurrencyRepository;
import com.example.finished.repository.UserRepository;
import com.example.finished.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements SimpleCRUD<Integer, AccountDto> {
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    @Override
    public ResponseDto<AccountDto> create(AccountDto dto) {
        Optional<Account> optionalAccount = this.accountRepository.findByAccountIdAndDeletedAtIsNull(dto.getUserId());
        if (optionalAccount.isEmpty()){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message("User is not found")
                    .build();
        }
        Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(dto.getUserId());
        if (optionalUser.isEmpty()){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message("User is not found")
                    .build();
        }
        Optional<Currency> optionalCurrency = this.currencyRepository.findByCurrencyIdAndDeletedAtIsNull(dto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
           return ResponseDto.<AccountDto>builder()
                   .code(-2)
                   .message("Currency is not found")
                   .build();
        }
        try {
            Account account = optionalAccount.get();
            account.setCreatedAt(LocalDateTime.now());
            this.accountRepository.save(account);
            return ResponseDto.<AccountDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.accountMapper.toDto(account))
                    .build();
        }catch (Exception e){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message(String.format("Account while saving error::%s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<AccountDto> get(Integer entityId) {
        Optional<Account> optionalAccount = this.accountRepository.findByAccountIdAndDeletedAtIsNull(entityId);
        if (optionalAccount.isEmpty()){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message("Account is not found")
                    .build();
        }
        return ResponseDto.<AccountDto>builder()
                .success(true)
                .message("OK")
                .data(this.accountMapper.toDto(optionalAccount.get()))
                .build();
    }

    @Override
    public ResponseDto<AccountDto> update(AccountDto dto, Integer entityId) {
        Optional<Account> optionalAccount = this.accountRepository.findByAccountIdAndDeletedAtIsNull(entityId);
        if (optionalAccount.isEmpty()){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message("Account is not found")
                    .build();
        }
        Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(dto.getUserId());
        if (optionalUser.isEmpty()){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message("User is not found")
                    .build();
        }
        Optional<Currency> optionalCurrency = this.currencyRepository.findByCurrencyIdAndDeletedAtIsNull(dto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message("Currency is not found")
                    .build();
        }
        try {
            Account account = optionalAccount.get();
            this.accountMapper.update(dto, account);
            account.setUpdatedAt(LocalDateTime.now());
            this.accountRepository.save(account);
            return ResponseDto.<AccountDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.accountMapper.toDto(account))
                    .build();
        }catch (Exception e){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message(String.format("Account while updating error :: %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<AccountDto> delete(Integer entityId) {
        Optional<Account> optionalAccount = this.accountRepository.findByAccountIdAndDeletedAtIsNull(entityId);
        if (optionalAccount.isEmpty()){
            return ResponseDto.<AccountDto>builder()
                    .code(-2)
                    .message("Account is not found")
                    .build();
        }
        Account account = optionalAccount.get();
        account.setDeletedAt(LocalDateTime.now());
        this.accountRepository.save(account);
        return ResponseDto.<AccountDto>builder()
                .success(true)
                .message("OK")
                .data(this.accountMapper.toDto(account))
                .build();
    }
}
