package com.skedio.administrationservice.applicationmanagement.references.client.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ApClient(
        Long clientId,
        UUID clientUuid,
        String clientName,
        String avatar,
        Boolean enabled,
        String description
) {}
