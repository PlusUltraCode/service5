package org.delivery.api.domain.user.converter;

import org.delivery.api.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.exception.ApiException;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it->{
                    //request -> entity

                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,"request null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it->{
                    //쌔거인 entity -> userResponse 바꿔줘야됌
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .status(userEntity.getStatus())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,"usrEntity null"));
    }
}
