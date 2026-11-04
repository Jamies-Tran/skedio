package com.skedio.administrationservice.usermanagement.user.controller.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        Long userId,
        UUID userUuid,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone,
        String statusCode,
        String statusName,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
}
