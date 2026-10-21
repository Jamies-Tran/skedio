package com.skedio.administrationservice.clientmanagement.client.domain.argument;

import java.util.UUID;

public record ClientDeleteAllByApplicationUuid(
        UUID applicationUuid
) {
}
