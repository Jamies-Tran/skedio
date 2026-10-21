package com.skedio.administrationservice.clientmanagement.client.controller.models;

import jakarta.validation.constraints.NotBlank;

public record ClientCreateRequest(
        @NotBlank(message = "clientName must not be null")
        String clientName,
        String avatar,
        String description,
        Boolean enabled
) {
}
