package com.khoavm.lifeup.module.common.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
public class ResponseDto <T>{
    private Integer statusCode;
    private T data;
    private String message;

    public ResponseDto() {

    }

    public static <T> ResponseDto<T> CreatedResponse(T data){
        return new ResponseDto<>(HttpStatus.CREATED.value(), data, "Create Successfully");
    }

    public static <T> ResponseDto<T> OkResponse(T data){
        return new ResponseDto<>(HttpStatus.OK.value(), data, "Create Successfully");
    }

}
