package com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ApApplicationUser(
    Long userApplicationId,
    UUID userUuid,
    String username,
    String firstName,
    String lastName,
    String email,
    String phone,
    UUID applicationUuid,
    String applicationName,
    String createdBy,
    LocalDateTime createdAt,
    String updatedBy,
    LocalDateTime updatedAt
) {
    public static ApApplicationUser from(UUID userUuid, UUID applicationUuid) {
        return ApApplicationUser.builder()
                .userUuid(userUuid)
                .applicationUuid(applicationUuid)
                .build();
    }
}
