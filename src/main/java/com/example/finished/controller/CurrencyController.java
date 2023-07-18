package com.example.finished.controller;

import com.example.finished.dto.CurrencyDto;
import com.example.finished.dto.ResponseDto;
import com.example.finished.dto.SimpleCRUD;
import com.example.finished.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "currency")
@RequiredArgsConstructor
public class CurrencyController implements SimpleCRUD<Integer, CurrencyDto> {

    private final CurrencyService currencyService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<CurrencyDto> create(@RequestBody CurrencyDto dto) {
        return this.currencyService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<CurrencyDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.currencyService.get(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<CurrencyDto> update(@RequestBody CurrencyDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.currencyService.update(dto, entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<CurrencyDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.currencyService.delete(entityId);
    }
}
