package com.example.session.controller;

import com.example.session.model.LoginRequest;
import com.example.session.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(
            @RequestBody
            LoginRequest loginRequest,
            HttpSession httpSession
    ){
        userService.login(loginRequest,httpSession);
    }

}
