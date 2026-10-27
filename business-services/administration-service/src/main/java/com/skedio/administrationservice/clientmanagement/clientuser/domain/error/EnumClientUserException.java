package com.skedio.administrationservice.clientmanagement.clientuser.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumClientUserException implements ApplicationErrorCode {
    CLIENT_NOT_FOUND("CU_001", "Client not found or disabled"),
    USER_DUP_IN_LIST("CU_002", "User duplicated"),
    USER_DUP_IN_DB("CU_003", "User existed"),;

    String code;
    String message;
}
