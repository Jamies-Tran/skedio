package com.skedio.administrationservice.scopemanagement.scope.controller.models;

public record ScopeUpdateRequest(
        String scopeName,
        String description,
        Boolean enabled
) {
}
