package com.skedio.administrationservice.clientmanagement.clientscope.controller.models;

import java.util.UUID;

public record ClientScopeResponse(
        Long clientScopeId,
        UUID clientUuid,
        UUID scopeUuid,
        String scopeName,
        String resourcePath
) {
}
