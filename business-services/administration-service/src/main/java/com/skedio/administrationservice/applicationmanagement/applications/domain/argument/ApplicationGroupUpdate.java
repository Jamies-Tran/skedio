package com.skedio.administrationservice.applicationmanagement.applications.domain.argument;

import com.skedio.administrationservice.applicationmanagement.applications.controller.models.ApplicationGroupUpdateRequest;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ApplicationGroupUpdate(
        UUID applicationUuid,
        String applicationName,
        String avatar,
        String description
) {
    public static ApplicationGroupUpdate from(UUID applicationUuid, ApplicationGroupUpdateRequest request) {
        return ApplicationGroupUpdate.builder()
                .applicationUuid(applicationUuid)
                .applicationName(request.applicationGroupName())
                .avatar(request.avatar())
                .description(request.description())
                .build();
    }
}
