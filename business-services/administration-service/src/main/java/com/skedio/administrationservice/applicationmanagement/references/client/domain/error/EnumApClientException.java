package com.skedio.administrationservice.applicationmanagement.references.client.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumApClientException implements ApplicationErrorCode {
    APP_NOT_FOUND("APP_001", "Application Not Found"),;

    String code;
    String message;
}
