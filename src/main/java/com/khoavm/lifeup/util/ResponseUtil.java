package com.khoavm.lifeup.util;

import com.khoavm.lifeup.module.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static ResponseEntity<ResponseDto> DefaultCreateSuccessResponse(Object data){
        return Response(HttpStatus.CREATED, "Create successfully", data);
    }
    public static ResponseEntity<ResponseDto> DefaultSuccessResponse(Object data){
        return Response(HttpStatus.OK, "success", data);
    }

    public static ResponseEntity<ResponseDto> Response(HttpStatus status, String message, Object data){
        var responseBody = ResponseDto.builder().statusCode(status.value())
                .message(message)
                .data(data).build();
        return ResponseEntity.status(status).body(responseBody);
    }
}
