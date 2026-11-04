package com.skedio.administrationservice.clientmanagement.references.clientuser.controller.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID userUuid,
        String username,
        String firstName,
        String lastName,
        String phone,
        String email,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
}
