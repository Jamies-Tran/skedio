package com.skedio.administrationservice.usermanagement.user.domain.argument;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import lombok.NonNull;

import java.util.UUID;

public record UserUpdate(
        @NonNull
        UUID userUuid,
        @NonNull
        User update
) {
}
