package com.skedio.administrationservice.applicationmanagement.applicationuser.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ApplicationUser(
    Long userApplicationId,
    UUID userUuid,
    String username,
    String firstName,
    String lastName,
    String email,
    String phone,
    UUID applicationUuid,
    String applicationName
) {
    public static ApplicationUser from(UUID userUuid, UUID applicationUuid) {
        return ApplicationUser.builder()
                .userUuid(userUuid)
                .applicationUuid(applicationUuid)
                .build();
    }
}
