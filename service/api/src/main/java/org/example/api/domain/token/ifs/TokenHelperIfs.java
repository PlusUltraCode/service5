package org.example.api.domain.token.ifs;

import org.example.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Component;

import java.util.Map;


public interface TokenHelperIfs {

    TokenDto issueAccessToken(Map<String , Object> data);

    TokenDto issuedRefreshToken(Map<String , Object> data);

    Map<String, Object> validationTokenWithThrow(String token);
}
