package com.example.finished.service.mapper;

import com.example.finished.dto.AccountDto;
import com.example.finished.model.Account;
import jakarta.persistence.OneToMany;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public abstract AccountDto toDto(Account account);
    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Account toEntity(AccountDto accountDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(AccountDto accountDto, @MappingTarget Account account);
}
