package com.example.exception.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/b")
public class RestApiBController {


    @GetMapping("/hello")
    public void hello(){
        throw new NumberFormatException("number format exception");
    }
}
