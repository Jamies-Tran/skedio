package com.skedio.administrationservice.usermanagement.userapplication.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumUserApplicationException implements ApplicationErrorCode {
    USER_APPLICATION_DUPLICATED("USER_APP_001", "User exists in application"),
    USER_NOT_FOUND("USER_APP_002", "User not exist"),
    APPLICATION_NOT_FOUND("USER_APP_003", "Application not exist"),;

    String code;
    String message;
}
