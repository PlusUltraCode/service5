package org.example.db.user;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.db.BaseEntity;
import org.example.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = true) //부모클래스도 비교 가능하게 된다
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;


    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}