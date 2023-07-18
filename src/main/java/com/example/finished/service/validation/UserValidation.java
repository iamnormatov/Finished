package com.example.finished.service.validation;

import com.example.finished.dto.ErrorDto;
import com.example.finished.dto.UserDto;
import com.example.finished.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final UserRepository userRepository;
    public List<ErrorDto> validate(UserDto dto){
        List<ErrorDto> list = new ArrayList<>();
        if (StringUtils.isBlank(dto.getFirstName())){
            list.add(new ErrorDto("firstName", "Firstname cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getLastName())){
            list.add(new ErrorDto("lastName", "Lastname cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getUserName())){
            list.add(new ErrorDto("userName", "Username cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getEmail())){
            list.add(new ErrorDto("email", "Email cannot be null or empty"));
        } else if (this.userRepository.existsByEmail(dto.getEmail())) {
            list.add(new ErrorDto("email", String.format("This email %s already exist!", dto.getEmail())));
        }
        if (StringUtils.isBlank(dto.getPassword())){
            list.add(new ErrorDto("password", "Password cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getPhoneNumber())){
            list.add(new ErrorDto("phoneNumber", "Phone Number cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getBirthDate())){
            list.add(new ErrorDto("birthData", "Birth Data cannot be null or empty"));
        }
        if (checkEmailPattern(dto.getEmail())){
            list.add(new ErrorDto("email", String.format("Given %s The email was not valid", dto.getEmail())));
        }
        return list;
    }
    public boolean checkEmailPattern(String email){
        String[] array = email.split("@");
        if (array.length == 2){
            return !array[1].equals("gmail.com");
        }else {
            return true;
        }
    }

}
