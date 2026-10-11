package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import jakarta.validation.constraints.NotBlank;

public record ApplicationGroupUpdateRequest(
        @NotBlank(message = "Name must not null")
        String applicationGroupName,
        String avatar,
        String description
) {
}
