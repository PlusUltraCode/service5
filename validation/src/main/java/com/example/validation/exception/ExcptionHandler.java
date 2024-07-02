package com.example.validation.exception;


import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExcptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Api> exceptionInfo(
            MethodArgumentNotValidException exception
    ){
        log.error("", exception);

        List<String> errorMessageList = exception.getFieldErrors().stream()
                .map(it -> {
                    String format = "%s {%s} 은 %s";
                    String message = String.format(format,it.getField(),it.getRejectedValue(),it.getDefaultMessage());
                    return message;
                }).toList();

        Api.Error error = Api.Error
                .builder()
                .errorList(errorMessageList)
                .build();

        Api errorResponse = Api.builder()
                .error(error)
                .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .resultMessage(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }
}
