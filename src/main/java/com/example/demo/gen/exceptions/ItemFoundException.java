package com.example.demo.gen.exceptions;

import com.example.demo.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ItemFoundException extends GenBusinessException{

    public ItemFoundException(BaseErrorMessage message) {
        super(message);
    }
}
