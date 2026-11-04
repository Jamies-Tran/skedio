package com.skedio.administrationservice.clientmanagement.references.clientscope.domain;

import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.argument.ClientScopeAssign;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ClClientScope(
        Long clientScopeId,
        UUID clientUuid,
        UUID scopeUuid,
        String scopeName,
        String resourcePath,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static List<ClClientScope> from(UUID clientUuid, List<ClientScopeAssign> assigns) {
        return assigns.stream()
                .map(a -> ClClientScope.builder()
                        .clientUuid(clientUuid)
                        .scopeUuid(a.scopeUuid())
                        .resourcePath(a.resourcePath())
                        .build())
                .toList();
    }
}
