package com.example.exception.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Api<T> {

    private String resultCode;
    private String resultMessage;
    private T data;
}
