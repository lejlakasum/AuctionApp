package com.example.auctionapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {

        CustomException customException = new CustomException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now(),
                e
        );

        return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
    }
}
