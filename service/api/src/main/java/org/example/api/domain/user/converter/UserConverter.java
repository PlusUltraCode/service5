package org.example.api.domain.user.converter;


import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Converter;
import org.example.api.domain.user.controller.model.UserRegisterRequest;
import org.example.api.domain.user.controller.model.UserResponse;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.db.user.UserEntity;
import org.example.db.user.enums.UserStatus;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it ->{

                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"UserRegisterRequest Null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it->{
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .status(UserStatus.REGISTERED)
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();

                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"UserEntity Null"));
    }
}
