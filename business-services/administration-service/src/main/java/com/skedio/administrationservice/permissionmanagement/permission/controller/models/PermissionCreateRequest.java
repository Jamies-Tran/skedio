package com.skedio.administrationservice.permissionmanagement.permission.controller.models;

import jakarta.validation.constraints.NotBlank;

public record PermissionCreateRequest(
        @NotBlank(message = "permissionName must not be null")
        String permissionName,
        String description,
        Boolean enabled
) {
}
