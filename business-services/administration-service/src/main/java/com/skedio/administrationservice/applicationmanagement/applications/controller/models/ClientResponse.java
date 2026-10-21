package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import java.util.UUID;

public record ClientResponse(
        Long clientId,
        UUID clientUuid,
        String clientName,
        String avatar,
        String description,
        Boolean enabled
) {
}
