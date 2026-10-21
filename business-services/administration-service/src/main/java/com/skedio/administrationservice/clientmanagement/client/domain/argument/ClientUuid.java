package com.skedio.administrationservice.clientmanagement.client.domain.argument;

import lombok.NonNull;

import java.util.UUID;

public record ClientUuid(
        @NonNull
        UUID uuid
) {
}
