package com.skedio.administrationservice.applicationmanagement.applicationuser.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumApplicationUserException implements ApplicationErrorCode {
    USER_APPLICATION_DUPLICATED("APP_USER_001", "User exists in application"),
    USER_NOT_EXIST_ALL("APP_USER_002", "Some users in list not exists"),
    APPLICATION_NOT_FOUND("APP_USER_003", "Application not exist"),;

    String code;
    String message;
}
