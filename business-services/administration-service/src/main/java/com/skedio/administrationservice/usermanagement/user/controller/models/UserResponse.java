package com.skedio.administrationservice.usermanagement.user.controller.models;

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
        Boolean deleted
) {
}
