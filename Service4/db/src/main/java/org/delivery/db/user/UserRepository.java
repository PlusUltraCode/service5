package org.delivery.db.user;

import ch.qos.logback.core.spi.ErrorCodes;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    //email,pass,stats
    //select * from user where email and password and status order by id desc limit 1

    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status );

    //id,status
    //select * from user where id and status order by id desc limit 1

    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id , UserStatus status);

}
