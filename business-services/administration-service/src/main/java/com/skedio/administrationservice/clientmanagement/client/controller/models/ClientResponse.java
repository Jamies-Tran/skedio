package com.skedio.administrationservice.clientmanagement.client.controller.models;

import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientResponse(
        Long clientId,
        UUID applicationUuid,
        UUID clientUuid,
        String clientName,
        String avatar,
        String description,
        Boolean enabled,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {}
