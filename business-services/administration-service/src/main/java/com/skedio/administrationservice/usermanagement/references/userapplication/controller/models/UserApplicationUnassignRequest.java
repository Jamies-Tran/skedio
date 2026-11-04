package com.skedio.administrationservice.usermanagement.references.userapplication.controller.models;

import java.util.UUID;

public record UserApplicationUnassignRequest(
        UUID userUuid,
        UUID applicationUuid
) {
}
