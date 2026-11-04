package com.skedio.administrationservice.usermanagement.references.userapplication.controller.models;

import java.util.UUID;

public record UserApplicationAssignRequest(
        UUID userUuid,
        UUID applicationUuid
) {
}
