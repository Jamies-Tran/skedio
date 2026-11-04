package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApplicationGroupResponse(
        Long applicationId,
        UUID applicationUuid,
        String applicationName,
        String avatar,
        String description,
        Boolean enabled,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
}
