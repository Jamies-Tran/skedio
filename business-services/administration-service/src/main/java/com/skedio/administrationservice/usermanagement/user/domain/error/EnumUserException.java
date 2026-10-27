package com.skedio.administrationservice.usermanagement.user.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumUserException implements ApplicationErrorCode {
    USER_DUPLICATED("USER_001", "User duplicated"),
    USER_NOT_FOUND("USER_002", "User not found"),
    USER_IN_USE("USER_003", "User in used"),;

    String code;
    String message;
}
