package com.bescobarres.football.domain.exception;

public class ApiRequestExceptionNotFound extends RuntimeException{
    public ApiRequestExceptionNotFound(String message) {
        super(message);
    }

    public ApiRequestExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRequestExceptionNotFound() {

    }
}
