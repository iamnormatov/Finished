package com.example.finished.service;

import com.example.finished.dto.CurrencyDto;
import com.example.finished.dto.ErrorDto;
import com.example.finished.dto.ResponseDto;
import com.example.finished.dto.SimpleCRUD;
import com.example.finished.model.Currency;
import com.example.finished.repository.CurrencyRepository;
import com.example.finished.service.mapper.CurrencyMapper;
import com.example.finished.service.validation.CurrencyValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService implements SimpleCRUD<Integer, CurrencyDto> {
    private final CurrencyMapper currencyMapper;
    private final CurrencyRepository currencyRepository;
    private final CurrencyValidation currencyValidation;

    @Override
    public ResponseDto<CurrencyDto> create(CurrencyDto dto) {
        List<ErrorDto> error = this.currencyValidation.validate(dto);
        if (!error.isEmpty()){
            return ResponseDto.<CurrencyDto>builder()
                    .code(-3)
                    .error(error)
                    .build();
        }
        try {
            Currency currency = this.currencyMapper.toEntity(dto);
            currency.setCreatedAt(LocalDateTime.now());
            this.currencyRepository.save(currency);
            return ResponseDto.<CurrencyDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.currencyMapper.toDto(currency))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CurrencyDto>builder()
                    .code(-2)
                    .message(String.format("Currency while saving error :: %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<CurrencyDto> get(Integer entityId) {
       return this.currencyRepository.findByCurrencyIdAndDeletedAtIsNull(entityId)
                .map(currency -> ResponseDto.<CurrencyDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.currencyMapper.toDtoWithCurrency(currency))
                        .build()
                )
                .orElse(ResponseDto.<CurrencyDto>builder()
                        .code(-2)
                        .message("User is not found")
                        .build()
                );
    }

    @Override
    public ResponseDto<CurrencyDto> update(CurrencyDto dto, Integer entityId) {
        List<ErrorDto> error = this.currencyValidation.validate(dto);
        if (!error.isEmpty()){
            return ResponseDto.<CurrencyDto>builder()
                    .code(-3)
                    .error(error)
                    .build();
        }
        try {
            return this.currencyRepository.findByCurrencyIdAndDeletedAtIsNull(entityId)
                    .map(currency -> {
                        this.currencyMapper.update(dto, currency);
                        currency.setUpdatedAt(LocalDateTime.now());
                        this.currencyRepository.save(currency);
                        return ResponseDto.<CurrencyDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.currencyMapper.toDto(currency))
                                .build();
                    })
                    .orElse(ResponseDto.<CurrencyDto>builder()
                            .code(-2)
                            .message("User is not found")
                            .build()
                    );
        }catch (Exception e){
            return ResponseDto.<CurrencyDto>builder()
                    .code(-2)
                    .message(String.format("Currency while updating error :: %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<CurrencyDto> delete(Integer entityId) {
       return this.currencyRepository.findByCurrencyIdAndDeletedAtIsNull(entityId)
                .map(currency -> {
                    currency.setDeletedAt(LocalDateTime.now());
                    this.currencyRepository.save(currency);
                    return ResponseDto.<CurrencyDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.currencyMapper.toDto(currency))
                            .build();
                })
                .orElse(ResponseDto.<CurrencyDto>builder()
                        .code(-2)
                        .message("User is not found")
                        .build()
                );
    }
}
