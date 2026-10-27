package com.skedio.administrationservice.scopemanagement.scope.controller.models;

import jakarta.validation.constraints.NotBlank;

public record ScopeCreateRequest(
        @NotBlank(message = "Scope name must not be null")
        String scopeName,
        String description,
        Boolean enabled
) {
}
