package com.skedio.administrationservice.applicationmanagement.applications.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumApplicationGroupException implements ApplicationErrorCode {
    APP_NOT_FOUND("APP_001", "Application Not Found"),
    APP_DUPLICATE_IN_LIST("APP_002", "Application duplicate in list"),
    APP_EXISTS("APP_003", "Application exists"),;

    String code;
    String message;
}
