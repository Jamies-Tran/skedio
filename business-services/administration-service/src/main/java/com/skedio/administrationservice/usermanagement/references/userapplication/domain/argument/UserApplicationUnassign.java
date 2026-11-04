package com.skedio.administrationservice.usermanagement.references.userapplication.domain.argument;

import com.skedio.administrationservice.usermanagement.references.userapplication.controller.models.UserApplicationUnassignRequest;
import lombok.NonNull;

import java.util.UUID;

public record UserApplicationUnassign(
        @NonNull
        UUID userUuid,
        @NonNull
        UUID applicationUuid
) {
    public static UserApplicationUnassign from(UserApplicationUnassignRequest request) {
        return new UserApplicationUnassign(request.userUuid(), request.applicationUuid());
    }
}
