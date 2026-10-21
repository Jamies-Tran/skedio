package com.skedio.administrationservice.clientmanagement.client.domain.error;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumClientException implements ApplicationErrorCode {
    APP_NOT_FOUND("CLIENT_001", "Application not found"),
    CLIENT_DUP_IN_LIST("CLIENT_001", "Client name duplicate in list"),
    CLIENT_DUP_IN_DB("CLIENT_002", "Client name duplicated"),
    CLIENT_NOT_FOUND("CLIENT_003", "Client not found");

    String code;
    String message;
}
