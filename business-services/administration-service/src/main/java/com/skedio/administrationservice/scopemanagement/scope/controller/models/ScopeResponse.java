package com.skedio.administrationservice.scopemanagement.scope.controller.models;

import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScopeResponse(
        Long scopeId,
        UUID scopeUuid,
        String scopeName,
        String description,
        Boolean enabled,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
