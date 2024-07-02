package com.example.restapi.controller;


import com.example.restapi.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/api/v1")
class ResponseApiController {

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<UserRequest> Info(){

        var user = new UserRequest();
        user.setName("1");
        user.setEmail("1");
        user.setPhoneNumber("1");

        var response = ResponseEntity
                .status(HttpStatus.OK)
                .header("bao","good")
                .body(user);
        return response;

    }
}