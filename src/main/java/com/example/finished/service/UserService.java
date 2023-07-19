package com.example.finished.service;

import com.example.finished.dto.ErrorDto;
import com.example.finished.dto.ResponseDto;
import com.example.finished.dto.SimpleCRUD;
import com.example.finished.dto.UserDto;
import com.example.finished.model.User;
import com.example.finished.repository.UserRepository;
import com.example.finished.service.mapper.UserMapper;
import com.example.finished.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCRUD<Integer, UserDto> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidation userValidation;

    @Override
    public ResponseDto<UserDto> create(UserDto dto) {
        List<ErrorDto> errors = this.userValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .error(errors)
                    .build();
        }
        try {
            User user = this.userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            this.userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.userMapper.toDto(user))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message(String.format("User while saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> get(Integer entityId) {
        return this.userRepository.findByUserIdAndDeletedAtIsNull(entityId)
                .map(user -> ResponseDto.<UserDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.userMapper.toDtoWithAccount(user))
                        .build())
                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .message("User is not found")
                        .build());

    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer entityId) {
        List<ErrorDto> errors = this.userValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .error(errors)
                    .build();
        }
        try {
            return this.userRepository.findByUserIdAndDeletedAtIsNull(entityId)
                    .map(user -> {
                        this.userMapper.update(dto, user);
                        user.setUpdatedAt(LocalDateTime.now());
                        this.userRepository.save(user);
                        return ResponseDto.<UserDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.userMapper.toDto(user))
                                .build();
                    })
                    .orElse(ResponseDto.<UserDto>builder()
                            .code(-1)
                            .message("User is not found")
                            .build());
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message(String.format("User while updating error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> delete(Integer entityId) {
       return this.userRepository.findByUserIdAndDeletedAtIsNull(entityId)
                .map(user -> {
                    user.setDeletedAt(LocalDateTime.now());
                    this.userRepository.save(user);
                    return ResponseDto.<UserDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.userMapper.toDto(user))
                            .build();
                })
                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .message("User is not found")
                        .build());
    }
}
