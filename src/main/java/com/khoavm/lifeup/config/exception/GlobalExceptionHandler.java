package com.khoavm.lifeup.config.exception;

import com.khoavm.lifeup.module.common.dto.ResponseDto;
import com.khoavm.lifeup.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGlobalException(Exception exception,
                                                             WebRequest webRequest) {

        var responseBody = ResponseDto.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
