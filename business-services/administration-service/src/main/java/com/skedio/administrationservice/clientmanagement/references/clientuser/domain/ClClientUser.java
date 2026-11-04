package com.skedio.administrationservice.clientmanagement.references.clientuser.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ClClientUser(
        Long clientUserId,
        UUID clientUuid,
        UUID userUuid,
        String username,
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
