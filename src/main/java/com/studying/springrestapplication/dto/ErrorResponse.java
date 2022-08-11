package com.studying.springrestapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse(HttpStatus httpStatus, Exception exception) {
        this.error = httpStatus.getReasonPhrase();
        this.message = exception.getMessage();
    }

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
