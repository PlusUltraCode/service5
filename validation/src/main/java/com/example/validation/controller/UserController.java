package com.example.validation.controller;


import com.example.validation.model.Api;
import com.example.validation.model.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("")
    public Api<User> userInfo(
            @Valid
            @RequestBody User user
    ) {

        log.info("",user);

        Api<User> response = Api.<User>builder()
                .data(user)
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .build();

        return response;
    }
}
