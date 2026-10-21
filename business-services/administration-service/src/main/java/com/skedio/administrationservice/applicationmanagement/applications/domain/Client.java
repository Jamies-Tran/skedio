package com.skedio.administrationservice.applicationmanagement.applications.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Client(
        Long clientId,
        UUID clientUuid,
        String clientName,
        String avatar,
        Boolean enabled,
        String description
) {}
