package com.example.finished.service.mapper;

import com.example.finished.dto.UserDto;
import com.example.finished.model.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", imports = Collectors.class)
@RequiredArgsConstructor
public abstract class UserMapper {
    protected AccountMapper accountMapper;
    @Mapping(target = "account", ignore = true)
    public abstract UserDto toDto(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    public abstract User toEntity(UserDto userDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(UserDto userDto, @MappingTarget User user);

    @Mapping(target = "account", expression = "java(user.getAccounts().stream().map(this.accountMapper::toDto).collect(Collectors.toSet()))")
    public abstract UserDto toDtoWithAccount(User user);

//    public void view(User user){
//        UserDto userDto = new UserDto();
//        userDto.setAccount(user.getAccounts().stream().map(this.accountMapper::toDto).collect(Collectors.toSet()));
//    }
}
