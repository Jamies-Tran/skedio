package com.skedio.administrationservice.applicationmanagement.applicationuser.controller.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record ApplicationUserUuidListRequest(
        @NotNull(message = "User uuid must not be null")
        @Size(min = 1, message = "Must have at least one element")
        List<UUID> userUuids
) {
}
