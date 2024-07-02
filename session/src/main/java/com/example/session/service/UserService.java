package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public void login(
            LoginRequest loginRequest,
            HttpSession httpSession
    ){

        String id = loginRequest.getId();
        String pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){

            var userDto = optionalUser.get();

            if(userDto.getPassword().equals(pw)){
                httpSession.setAttribute("USER",userDto);
            }else{
                throw new RuntimeException("비밀번호 틀림");
            }

        }else{
            throw new RuntimeException("없다.");
        }
    }
}
