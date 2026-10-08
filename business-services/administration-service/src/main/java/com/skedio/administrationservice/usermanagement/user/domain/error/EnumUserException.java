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
    USER_ID_NOT_FOUND("USER_003", "User id null"),
    USER_UUID_NOT_FOUND("USER_004", "User uuid null"),;

    String code;
    String message;
}
