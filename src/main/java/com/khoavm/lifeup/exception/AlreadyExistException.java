package com.khoavm.lifeup.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends CustomException{
    public AlreadyExistException(String message) {
        super(message, HttpStatus.CONFLICT);

    }
}
