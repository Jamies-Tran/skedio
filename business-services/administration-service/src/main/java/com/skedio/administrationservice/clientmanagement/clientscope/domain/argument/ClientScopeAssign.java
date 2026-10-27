package com.skedio.administrationservice.clientmanagement.clientscope.domain.argument;

import com.skedio.administrationservice.clientmanagement.clientscope.controller.models.ClientScopeAssignRequest;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ClientScopeAssign(
        UUID scopeUuid,
        String resourcePath
) {
    public static List<ClientScopeAssign> from(List<ClientScopeAssignRequest> requests) {
        return requests.stream()
                .map(request -> ClientScopeAssign.builder()
                        .scopeUuid(request.scopeUuid())
                        .resourcePath(request.resourcePath())
                        .build())
                .toList();
    }
}
