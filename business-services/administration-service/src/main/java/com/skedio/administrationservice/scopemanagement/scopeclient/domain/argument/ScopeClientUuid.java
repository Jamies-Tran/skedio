package com.skedio.administrationservice.scopemanagement.scopeclient.domain.argument;

import com.skedio.administrationservice.scopemanagement.scopeclient.controller.models.ScopeUnassignRequest;

import java.util.UUID;

public record ScopeClientUuid(
        UUID clientUuid,
        UUID scopeUuid
) {
    public static ScopeClientUuid from(ScopeUnassignRequest request) {
        UUID clientUuid = request.clientUuid();
        UUID scopeUuid = request.scopeUuid();
        return new ScopeClientUuid(clientUuid, scopeUuid);
    }
}
