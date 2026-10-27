package com.skedio.administrationservice.scopemanagement.scope.domain.argument;

import com.skedio.administrationservice.scopemanagement.scope.controller.models.ScopeUpdateRequest;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ScopeUpdate(
        UUID scopeUuid,
        String scopeName,
        String description,
        Boolean enabled
) {
    public static ScopeUpdate from(UUID scopeUuid, ScopeUpdateRequest request) {
        return ScopeUpdate.builder()
                .scopeUuid(scopeUuid)
                .scopeName(request.scopeName())
                .description(request.description())
                .enabled(request.enabled())
                .build();
    }
}
