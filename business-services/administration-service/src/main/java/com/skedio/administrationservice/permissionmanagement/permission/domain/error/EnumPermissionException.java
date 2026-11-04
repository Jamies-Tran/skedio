package com.skedio.administrationservice.permissionmanagement.permission.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumPermissionException implements ApplicationErrorCode {
    PERMISSION_DUP_NAME("PERMISSION_001", "Permission name already exists"),;

    String code;
    String message;
}
