package com.example.auctionapp.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomException {

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
    private final Throwable throwable;

    public CustomException(String message,
                           HttpStatus httpStatus,
                           LocalDateTime timestamp,
                           Throwable throwable) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
