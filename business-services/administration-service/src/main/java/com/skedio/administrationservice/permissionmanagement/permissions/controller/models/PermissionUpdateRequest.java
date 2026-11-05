package com.skedio.administrationservice.permissionmanagement.permissions.controller.models;

public record PermissionUpdateRequest(
        String permissionName,
        String description
) {
}
