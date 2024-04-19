package com.khoavm.lifeup.exception;

import org.springframework.http.HttpStatus;

public class UnAuthenticatedException extends CustomException{

    public UnAuthenticatedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
