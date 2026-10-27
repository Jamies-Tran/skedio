package com.skedio.administrationservice.clientmanagement.clientscope.controller.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClientScopeAssignRequest(
        @NotNull(message = "scope uuid must not be null")
        UUID scopeUuid,
        @NotBlank(message = "resource path must not be null")
        String resourcePath
) {
}
