package com.skedio.administrationservice.clientmanagement.client.controller.models;

import jakarta.validation.constraints.NotBlank;

public record ClientUpdateRequest(
        @NotBlank(message = "client name must not be null")
        String clientName,
        String avatar,
        String description,
        Boolean enabled
) {
}
