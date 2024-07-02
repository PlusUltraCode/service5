package org.example.api.domain.user.controller;


import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.UserSession;
import org.example.api.common.api.Api;
import org.example.api.domain.user.business.UserBusiness;
import org.example.api.domain.user.controller.model.UserResponse;
import org.example.api.domain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user
            ){

        var response = userBusiness.me(user);
        return Api.OK(response);
    }
}
