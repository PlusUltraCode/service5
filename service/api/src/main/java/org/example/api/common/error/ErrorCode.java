package org.example.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{

    OK(200,200,"Success"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400 ,"Error Request"),

    SEVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500,"Server Error"),

    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512,"Null point")
    ;

    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;



}
