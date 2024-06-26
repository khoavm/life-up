package com.khoavm.lifeup.config.response;

import com.khoavm.lifeup.exception.CustomException;
import com.khoavm.lifeup.module.common.dto.ResponseDto;
import com.khoavm.lifeup.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Object>> handleGlobalException(Exception exception,
                                                             WebRequest webRequest) {
        if(exception instanceof CustomException customException){
            return ResponseUtil.Response(customException.getStatus(), customException.getMessage(), null);
        }
        if(exception instanceof MethodArgumentNotValidException argumentNotValidException){
            Map<String, String> errors = new HashMap<>();
            argumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseUtil.Response(argumentNotValidException.getStatusCode(), argumentNotValidException.getMessage(), errors);
        }
        if(exception instanceof AuthenticationException authenticationException){
            return ResponseUtil.Response(HttpStatus.UNAUTHORIZED, authenticationException.getMessage(), null);
        }
        return ResponseUtil.Response(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), null);
    }
}
