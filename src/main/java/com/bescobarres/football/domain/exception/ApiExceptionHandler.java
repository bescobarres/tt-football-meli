package com.bescobarres.football.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        // 1. Create Payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))

        );
        // 2. Return response entity
        return new ResponseEntity<>(apiException, badRequest);

    }
    @ExceptionHandler(value = {ApiRequestExceptionNotFound.class})
    public ResponseEntity<Object> handleApiRequestNotFound(ApiRequestExceptionNotFound e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))

        );
        // 2. Return response entity
        return new ResponseEntity<>(apiException, notFound);

    }
}
