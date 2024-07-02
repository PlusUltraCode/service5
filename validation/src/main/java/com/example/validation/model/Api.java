package com.example.validation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {

    private String resultCode;
    private String resultMessage;
    private T data;
    private Error error;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Error{
        private List<String> errorList;
    }
}
