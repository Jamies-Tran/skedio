package com.skedio.administrationservice.usermanagement.user.domain.argument;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserGetDetail(
        Long userId,
        UUID userUuid
) {
    public static UserGetDetail getByUserId(Long userId) {
        return UserGetDetail.builder().userId(userId).build();
    }

    public static UserGetDetail getByUserUuid(UUID userUuid) {
        return UserGetDetail.builder().userUuid(userUuid).build();
    }
}
