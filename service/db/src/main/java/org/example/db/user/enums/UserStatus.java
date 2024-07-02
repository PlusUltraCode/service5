package org.example.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    REGISTERED("Register"),
    UNREGISTERED("UnRegister"),
    ;

    private final String description;

}
