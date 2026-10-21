package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import jakarta.validation.constraints.NotBlank;

public record ApplicationGroupUpdateRequest(
        String applicationGroupName,
        String avatar,
        String description,
        Boolean enabled
) {
}
