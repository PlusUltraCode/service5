package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.annotation.Business;
import org.delivery.api.annotation.Converter;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;



    //1. request -> entity 변환
    //2. entity -> service save
    //3 . save 된 entity 반환 response
    public UserResponse register(UserRegisterRequest request) {

        var newEntity = userConverter.toEntity(request);
        var entity = userService.register(newEntity);
        var response = userConverter.toResponse(entity);
        return response;
    }

    //1. request -> db에 있는 데이터를 가져와야됨
    //2. token 발행
    //3. 완료되면 userResponse 리턴

    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(),request.getPassword());


        //Todo 토큰 발행 구현해야 됨
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getWithThrow(userId);
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
