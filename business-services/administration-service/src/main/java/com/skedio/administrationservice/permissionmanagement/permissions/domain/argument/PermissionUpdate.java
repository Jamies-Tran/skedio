package com.skedio.administrationservice.permissionmanagement.permissions.domain.argument;

import com.skedio.administrationservice.permissionmanagement.permissions.controller.models.PermissionUpdateRequest;
import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;

@Builder
public record PermissionUpdate(
        @NonNull
        UUID permissionUuid,
        String permissionName,
        String description
) {
        public static PermissionUpdate from(UUID permissionUuid, PermissionUpdateRequest request) {
                return PermissionUpdate.builder()
                        .permissionUuid(permissionUuid)
                        .permissionName(request.permissionName())
                        .description(request.description())
                        .build();
        }
}
