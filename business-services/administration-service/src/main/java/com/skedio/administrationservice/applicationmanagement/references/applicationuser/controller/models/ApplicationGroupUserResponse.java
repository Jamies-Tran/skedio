package com.skedio.administrationservice.applicationmanagement.references.applicationuser.controller.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApplicationGroupUserResponse(
        Long userApplicationId,
        UUID userUuid,
        String firstName,
        String lastName,
        String email,
        String phone,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
}
