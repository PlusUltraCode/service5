package org.example.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUND(400,1404,"User not found"),

    ;

    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;

}
