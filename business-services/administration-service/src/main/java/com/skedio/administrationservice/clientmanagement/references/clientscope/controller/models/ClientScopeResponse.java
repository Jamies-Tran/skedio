package com.skedio.administrationservice.clientmanagement.references.clientscope.controller.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientScopeResponse(
        Long clientScopeId,
        UUID clientUuid,
        UUID scopeUuid,
        String scopeName,
        String resourcePath,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
}
