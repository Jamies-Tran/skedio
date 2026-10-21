package com.skedio.administrationservice.clientmanagement.client.domain.argument;

import com.skedio.administrationservice.clientmanagement.client.controller.models.ClientUpdateRequest;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ClientUpdate(
        UUID clientUuid,
        String clientName,
        String avatar,
        String description,
        Boolean enabled
) {
    public static ClientUpdate from(UUID clientUuid, ClientUpdateRequest request) {
        return ClientUpdate.builder()
                .clientUuid(clientUuid)
                .clientName(request.clientName())
                .avatar(request.avatar())
                .description(request.description())
                .enabled(request.enabled())
                .build();
    }
}
