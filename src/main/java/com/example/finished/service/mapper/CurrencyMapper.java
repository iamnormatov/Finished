package com.example.finished.service.mapper;

import com.example.finished.dto.AccountDto;
import com.example.finished.dto.CurrencyDto;
import com.example.finished.model.Currency;
import com.example.finished.model.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", imports = Collectors.class)
@RequiredArgsConstructor
public abstract class CurrencyMapper {
    protected AccountMapper accountMapper;
    @Mapping(target = "account", ignore = true)
    public abstract CurrencyDto toDto(Currency currency);

    @Mapping(target = "currencyId", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    public abstract Currency toEntity(CurrencyDto currencyDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(CurrencyDto currencyDto, @MappingTarget Currency currency);

    @Mapping(target = "account", expression = "java(currency.getAccount().stream().map(this.accountMapper::toDto).collect(Collectors.toSet()))")
    public abstract CurrencyDto toDtoWithCurrency(Currency currency);

    public void view(Currency currency){
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setAccount(currency.getAccount().stream().map(this.accountMapper::toDto).collect(Collectors.toSet()));
    }
}
