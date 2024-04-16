package com.khoavm.lifeup.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Getter
public class CustomException extends RuntimeException{
    private final HttpStatus status;

    public CustomException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }



}
