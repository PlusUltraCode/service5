package org.example.api.domain.user.business;


import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Business;
import org.example.api.domain.token.business.TokenBusiness;
import org.example.api.domain.token.controller.model.TokenResponse;
import org.example.api.domain.user.controller.model.UserLoginRequest;
import org.example.api.domain.user.controller.model.UserRegisterRequest;
import org.example.api.domain.user.controller.model.UserResponse;
import org.example.api.domain.user.converter.UserConverter;
import org.example.api.domain.user.model.User;
import org.example.api.domain.user.service.UserService;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService  userService;

    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;


    /*
    * 1. request -> entity 로 변환
    * 2. entity -> save
    * 3. save Entity -> response
    * 4. response return
    * */

    public UserResponse register(UserRegisterRequest request) {

        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
    }

    /*
    * 1. email password 를 가지고 사용자 체크
    * 2. user entity 로그인 확인
    * 3. token 생성
    * 4. token response
    * */
    public TokenResponse login(UserLoginRequest request) {

        var userEntity = userService.login(request.getEmail(),request.getPassword());
        //사용자 없으면 throw

        //TODO 토큰 생성
        var tokenResponse = tokenBusiness.issueToken(userEntity);

        return tokenResponse;

    }

    public UserResponse me(
            User user
    ) {
        var userEntity = userService.getUserWithThrow(user.getId());
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
