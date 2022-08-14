package com.studying.springrestapplication.handler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.studying.springrestapplication.dto.ErrorResponse;
import com.studying.springrestapplication.exception.EntityByIdNotFoundException;
import com.studying.springrestapplication.exception.UsernameIsTakenException;
import io.jsonwebtoken.JwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
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
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> messages = new ArrayList<>();
        messages.add("Unacceptable JSON");

        Throwable throwable = ex.getCause();
        addMoreDetailsAboutExceptionIfItIsEnumRelated(messages, throwable);

        ErrorResponse errorResponse = new ErrorResponse(status, messages);

        return new ResponseEntity<>(errorResponse, status);
    }

    private void addMoreDetailsAboutExceptionIfItIsEnumRelated(List<String> messages, Throwable throwable) {
        if (throwable instanceof InvalidFormatException) {
            InvalidFormatException formatException = (InvalidFormatException) throwable;

            Class<?> exceptionTargetType = formatException.getTargetType();

            if (exceptionTargetType != null && exceptionTargetType.isEnum()) {
                Object value = formatException.getValue();

                List<Reference> references = formatException.getPath();
                String fieldName = references.get(0).getFieldName();

                Object[] requiredValues = exceptionTargetType.getEnumConstants();

                messages.add(String.format("Invalid value '%s' for the field '%s'", value, fieldName));
                messages.add(String.format("The value must be one of: %s", Arrays.toString(requiredValues)));
            }
        }
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