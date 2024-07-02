package org.example.api.domain.token.service;


import lombok.RequiredArgsConstructor;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.token.controller.model.TokenResponse;
import org.example.api.domain.token.helper.JwtTokenHelper;
import org.example.api.domain.token.ifs.TokenHelperIfs;
import org.example.api.domain.token.model.TokenDto;
import org.example.db.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {


    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issuedRefreshToken(data);
    }

    public Long validationToken(String token) {
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var userId = map.get("userId");

        Objects.requireNonNull(userId, ()->{throw new ApiException(ErrorCode.NULL_POINT);});

        return Long.parseLong(userId.toString());
    }
}
