package com.skedio.administrationservice.permissionmanagement.permissions.domain;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionCreate;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionUpdate;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Permission(
        Long permissionId,
        UUID permissionUuid,
        @With
        String permissionName,
        @With
        String description,
        @With
        Boolean enabled,
        @With
        String search,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
    public Permission computeBeforeSave() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.permissionName);
        Boolean enabled = this.enabled != null ? this.enabled : true;
        return this
                .withSearch(search)
                .withEnabled(enabled);
    }

    public static Permission from(PermissionCreate command) {
        return Permission.builder()
                .permissionName(command.permissionName())
                .description(command.description())
                .enabled(command.enabled())
                .build();
    }

    public Permission apply(PermissionUpdate command) {
        String permissionName = command.permissionName();
        String description = command.description();
        return this
                .withPermissionName(permissionName)
                .withDescription(description);
    }

    public Permission enable() {
        Boolean enabled = true;
        return this
                .withEnabled(enabled);
    }

    public Permission disable() {
        Boolean enabled = false;
        return this
                .withEnabled(enabled);
    }
}
