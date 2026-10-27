package com.skedio.administrationservice.scopemanagement.scope.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumScopeException implements ApplicationErrorCode {
    SCOPE_DUPLICATE("SCOPE_001", "Scope name exists"),
    SCOPE_NOT_FOUND("SCOPE_002", "Scope not found"),
    SCOPE_IN_USE("SCOPE_003", "Scope in use"),;

    String code;
    String message;
}
