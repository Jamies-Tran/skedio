package com.skedio.administrationservice.usermanagement.references.userapplication.domain;

import com.skedio.administrationservice.usermanagement.references.userapplication.domain.argument.UserApplicationAssign;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UsUserApplication(
        Long userApplicationId,
        UUID userUuid,
        UUID applicationUuid
) {
    public static UsUserApplication from(UserApplicationAssign assign) {
        return UsUserApplication.builder()
                .userUuid(assign.userUuid())
                .applicationUuid(assign.applicationUuid())
                .build();
    }
}
