package com.skedio.administrationservice.permissionmanagement.permission.controller.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record PermissionResponse(
        Long permissionId,
        UUID permissionUuid,
        String permissionName,
        String description,
        Boolean enabled,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
}
