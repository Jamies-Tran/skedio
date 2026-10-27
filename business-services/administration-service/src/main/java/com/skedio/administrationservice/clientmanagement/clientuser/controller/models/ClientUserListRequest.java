package com.skedio.administrationservice.clientmanagement.clientuser.controller.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record ClientUserListRequest(
        @NotNull(message = "User uuid must not be null")
        @Size(min = 1, message = "User uuid must have at least one element")
        List<UUID> userUuids
) {
}
