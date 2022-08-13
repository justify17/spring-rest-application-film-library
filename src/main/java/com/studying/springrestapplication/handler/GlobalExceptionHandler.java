package com.studying.springrestapplication.handler;

import com.studying.springrestapplication.dto.ErrorResponse;
import com.studying.springrestapplication.exception.EntityByIdNotFoundException;
import com.studying.springrestapplication.exception.UsernameIsTakenException;
import io.jsonwebtoken.JwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            UsernameIsTakenException.class,
            BadCredentialsException.class,
            JwtException.class,
            EntityByIdNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleOrdinaryException(Exception exception) {
        HttpStatus status = BAD_REQUEST;
        List<String> messages = List.of(exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(status, messages);

        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(status, messages);

        return new ResponseEntity<>(errorResponse, status);
    }
}