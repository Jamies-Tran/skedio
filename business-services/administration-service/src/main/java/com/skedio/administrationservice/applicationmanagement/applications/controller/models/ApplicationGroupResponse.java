package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import java.util.UUID;

public record ApplicationGroupResponse(
        Long applicationId,
        UUID applicationUuid,
        String applicationName,
        String avatar,
        String description,
        Boolean enabled
) {
}
