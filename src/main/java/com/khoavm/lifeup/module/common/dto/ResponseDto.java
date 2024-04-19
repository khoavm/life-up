package com.khoavm.lifeup.module.common.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class ResponseDto <T>{
    private Integer statusCode;
    private T data;
    private String message;

    public ResponseDto(HttpStatusCode statusCode, T data, String message) {
        this.statusCode = statusCode.value();
        this.data = data;
        this.message = message;
    }

    public void setStatusCode(HttpStatusCode status){
        this.statusCode = status.value();
    }

}
