package com.example.session.db;

import com.example.session.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepository {

    private List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findByName(String id) {

        return userList.stream()
                .filter(it -> {
                    return it.getName().equals(id);
                }).findFirst();
    }


    @PostConstruct
    public void init() {
        userList.add(
                new UserDto("철수", "1234")
        );
        userList.add(
                new UserDto("동호", "1234")
        );
        userList.add(
                new UserDto("준수", "1234")
        );
    }
}
