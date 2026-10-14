package com.skedio.administrationservice.usermanagement.userapplication.domain.argument;

import com.skedio.administrationservice.usermanagement.userapplication.controller.models.UserApplicationAssignRequest;
import lombok.NonNull;

import java.util.UUID;

public record UserApplicationAssign(
        @NonNull
        UUID userUuid,
        @NonNull
        UUID applicationUuid
) {
    public static UserApplicationAssign from(UserApplicationAssignRequest request) {
        return new UserApplicationAssign(request.userUuid(), request.applicationUuid());
    }
}
