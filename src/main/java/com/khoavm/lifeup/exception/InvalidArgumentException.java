package com.khoavm.lifeup.exception;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends CustomException{
    public InvalidArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
