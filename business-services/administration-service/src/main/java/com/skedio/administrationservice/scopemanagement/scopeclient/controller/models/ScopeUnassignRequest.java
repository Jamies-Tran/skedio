package com.skedio.administrationservice.scopemanagement.scopeclient.controller.models;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ScopeUnassignRequest(
        @NotNull(message = "client uuid must not be null")
        UUID clientUuid,

        @NotNull(message = "scope uuid must not be null")
        UUID scopeUuid
) {
}
