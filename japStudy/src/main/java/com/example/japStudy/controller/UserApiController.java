package com.example.japStudy.controller;


import com.example.japStudy.db.UserEntity;
import com.example.japStudy.db.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/find-all")
    public List<UserEntity> findAll(){

        return userRepository.findAll();
    }

    @GetMapping("/save/{name}")
    public void autoSave(
            @PathVariable String name
    ){
        var user = UserEntity.builder()
                .name(name)
                .age(1234)
                .build()
                ;

        userRepository.save(user);
    }


}
