package com.khoavm.lifeup.util;

import com.khoavm.lifeup.module.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<ResponseDto<T>> Created(T data){
        return Response(HttpStatus.CREATED, "Create successfully", data);
    }
    public static <T> ResponseEntity<ResponseDto<T>> Ok(T data){
        return Response(HttpStatus.OK, "success", data);
    }

    public static <T> ResponseEntity<ResponseDto<T>> Response(HttpStatusCode status, String message, T data){
        var responseBody = new ResponseDto<T>();
        responseBody.setMessage(message);
        responseBody.setData(data);
        responseBody.setStatusCode(status.value());
        return ResponseEntity.status(status).body(responseBody);
    }
}
