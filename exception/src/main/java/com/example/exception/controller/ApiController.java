package com.example.exception.controller;

import com.example.exception.model.Api;
import com.example.exception.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    public static List<User> users = List.of(
            User.builder()
                    .id("1")
                    .age("25")
                    .build(),
            User.builder()
                    .id("2")
                    .age("30")
                    .build()
    );

    @GetMapping("/id/{userId}")
    public Api<User> userInfo(@PathVariable String userId) {

        User user = users.stream()
                .filter(it-> it.getId().equals(userId))
                .findFirst().get();

        Api<User> api = Api.<User>builder()
                .data(user)
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .build();

        return api;
    }
}
