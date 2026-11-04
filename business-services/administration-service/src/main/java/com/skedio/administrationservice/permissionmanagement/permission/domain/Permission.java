package com.skedio.administrationservice.permissionmanagement.permission.domain;

import com.skedio.administrationservice.permissionmanagement.permission.domain.argument.PermissionCreate;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Permission(
        Long permissionId,
        UUID permissionUuid,
        String permissionName,
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
                .description(command.permissionName())
                .enabled(command.enabled())
                .build();
    }
}
