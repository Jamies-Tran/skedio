package com.skedio.administrationservice.clientmanagement.clientuser.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClientUser(
        Long clientUserId,
        UUID clientUuid,
        UUID userUuid,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
