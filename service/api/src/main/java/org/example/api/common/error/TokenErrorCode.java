package org.example.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
// Token 의 경우 2000번대 에러코드 사용
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{

    INVALID_TOKEN(400,2000,"Token not useful"),
    EXPIRED_TOKEN(400,2001,"expired token"),
    TOKEN_EXCEPTION(400,2002,"don't know Token Error"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400,2003,"인증 헤더 토큰 없음"),
    ;

    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;

}
