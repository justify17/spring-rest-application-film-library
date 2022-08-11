package com.studying.springrestapplication.handler;

import com.studying.springrestapplication.dto.ErrorResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException exception) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, exception);

        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}
