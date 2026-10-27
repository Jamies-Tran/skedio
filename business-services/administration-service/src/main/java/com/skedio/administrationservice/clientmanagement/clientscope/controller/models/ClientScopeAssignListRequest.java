package com.skedio.administrationservice.clientmanagement.clientscope.controller.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientScopeAssignListRequest(
        @NotNull(message = "assigns must not be null")
        @Size(min = 1, message = "List must have at least one element")
        List<@Valid ClientScopeAssignRequest> assigns
) {
}
