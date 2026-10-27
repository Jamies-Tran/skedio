package com.skedio.administrationservice.clientmanagement.clientscope.domain;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeAssign;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ClientScope(
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
    public static List<ClientScope> from(UUID clientUuid, List<ClientScopeAssign> assigns) {
        return assigns.stream()
                .map(a -> ClientScope.builder()
                        .clientUuid(clientUuid)
                        .scopeUuid(a.scopeUuid())
                        .resourcePath(a.resourcePath())
                        .build())
                .toList();
    }
}
