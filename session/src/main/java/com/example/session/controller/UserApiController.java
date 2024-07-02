package com.example.session.controller;


import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    @GetMapping("/me")
    public UserDto me(
            HttpSession httpSession
    ){
        var userObject = httpSession.getAttribute("USER");

        if(userObject!=null){
            var userDto =(UserDto)userObject;
            return userDto;

        }else{
            return null;
        }
    }
}
