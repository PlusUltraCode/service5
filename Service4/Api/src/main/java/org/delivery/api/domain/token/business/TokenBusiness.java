package org.delivery.api.domain.token.business;


import lombok.RequiredArgsConstructor;
import org.delivery.api.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.api.exception.ApiException;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                .map(ue->{
                    return ue.getId();
                })
                .map(userId->{
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);

                    return tokenConverter.toTokenResponse(accessToken,refreshToken);
                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationToken(String accessToken){
        var userId = tokenService.validationToken(accessToken);
        return userId;

    }
}
