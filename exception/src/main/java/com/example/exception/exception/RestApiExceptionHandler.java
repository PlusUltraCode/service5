package com.example.exception.exception;


import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        log.error("RestApiExceptionHandler123123", e);
        return ResponseEntity.status(200).build();
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity exception(IndexOutOfBoundsException e) {
        log.error("RestApiExceptionHandlerInt", e);
        return ResponseEntity.status(200).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity exception(NoSuchElementException e) {
        log.error("",e);

        var response = Api.builder()
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .build();

        return ResponseEntity.status(404).body(response);
    }



}
