package com.skedio.administrationservice.clientmanagement.clientscope.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumClientScopeException implements ApplicationErrorCode {
    CLIENT_NOT_FOUND("CLIENT_SCOPE_001", "Client not exists or disabled"),
    ASSIGN_DUPLICATE_IN_LIST("CLIENT_SCOPE_002", "Scope exists in client"),
    ASSIGN_DUPLICATE_IN_DB("CLIENT_SCOPE_003", "Scope exists in client"),
    SCOPE_NOT_FOUND("CLIENT_SCOPE_004", "Scope not found"),;

    String code;
    String message;
}
