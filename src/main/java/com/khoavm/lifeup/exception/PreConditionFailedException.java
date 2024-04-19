package com.khoavm.lifeup.exception;

import org.springframework.http.HttpStatus;

public class PreConditionFailedException extends CustomException{
    public PreConditionFailedException(String message) {
        super(message, HttpStatus.PRECONDITION_FAILED);
    }
}
