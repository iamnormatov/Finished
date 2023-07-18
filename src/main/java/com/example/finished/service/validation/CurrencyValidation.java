package com.example.finished.service.validation;

import com.example.finished.dto.CurrencyDto;
import com.example.finished.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CurrencyValidation {
    public List<ErrorDto> validate(CurrencyDto currencyDto){
        List<ErrorDto> list = new ArrayList<>();
        if (StringUtils.isBlank(currencyDto.getName())){
            list.add(new ErrorDto("name", "Name cannot be null or empty"));
        }
        if (StringUtils.isBlank(currencyDto.getShortName())){
            list.add(new ErrorDto("shortName", "Short name cannot be null or empty"));
        }
        return list;
    }
}
