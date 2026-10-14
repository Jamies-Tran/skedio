package com.skedio.administrationservice.usermanagement.userapplication.controller.models;

import java.util.UUID;

public record UserApplicationAssignRequest(
        UUID userUuid,
        UUID applicationUuid
) {
}
