package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserEntity register(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it->{
                    //entity -> 추가 entity 만들고 save

                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                   return userRepository.save(userEntity);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,"usreENtity null"));
    }

    public UserEntity login(String email, String password) {

        var userEntity = getWithThrow(email,password);
        return userEntity;
    }

    public UserEntity getWithThrow(String email, String password){

        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email,password,UserStatus.REGISTERED)
                .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getWithThrow(Long userId){

        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId,UserStatus.REGISTERED)
                .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
