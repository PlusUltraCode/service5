package com.example.restapi.controller;


import com.example.restapi.model.BookRequest;
import com.example.restapi.model.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping("/post")
    public BookRequest post(
            @RequestBody BookRequest bookRequest
    ) {
        System.out.println(bookRequest);
        return bookRequest;

    }

    @PostMapping("/person")
    public UserRequest post(
            @RequestBody UserRequest userRequest
    ){
        System.out.println(userRequest);
        return userRequest;
    }
}
