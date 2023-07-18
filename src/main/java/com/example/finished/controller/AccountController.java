package com.example.finished.controller;

import com.example.finished.dto.AccountDto;
import com.example.finished.dto.ResponseDto;
import com.example.finished.dto.SimpleCRUD;
import com.example.finished.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "account")
@RequiredArgsConstructor
public class AccountController implements SimpleCRUD<Integer, AccountDto> {
    private final AccountService accountService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<AccountDto> create(@RequestBody AccountDto dto) {
        return this.accountService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<AccountDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.accountService.get(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<AccountDto> update(@RequestBody AccountDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.accountService.update(dto, entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<AccountDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.accountService.delete(entityId);
    }
}
