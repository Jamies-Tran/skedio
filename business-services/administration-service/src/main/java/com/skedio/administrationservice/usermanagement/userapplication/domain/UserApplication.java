package com.skedio.administrationservice.usermanagement.userapplication.domain;

import com.skedio.administrationservice.usermanagement.userapplication.domain.argument.UserApplicationAssign;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserApplication(
        Long userApplicationId,
        UUID userUuid,
        UUID applicationUuid
) {
    public static UserApplication from(UserApplicationAssign assign) {
        return UserApplication.builder()
                .userUuid(assign.userUuid())
                .applicationUuid(assign.applicationUuid())
                .build();
    }
}
