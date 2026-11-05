package com.skedio.administrationservice.permissionmanagement.permissions.domain.argument;

import com.skedio.administrationservice.permissionmanagement.permissions.controller.models.PermissionCreateRequest;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record PermissionCreate(
        @NonNull
        String permissionName,
        String description,
        Boolean enabled
) {
        public static PermissionCreate from(PermissionCreateRequest request) {
                return PermissionCreate.builder()
                        .permissionName(request.permissionName())
                        .description(request.description())
                        .enabled(request.enabled())
                        .build();
        }
}
