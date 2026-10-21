package com.skedio.administrationservice.usermanagement.user.domain.argument;

import com.skedio.administrationservice.usermanagement.user.controller.models.UserUpdateRequest;
import com.skedio.administrationservice.usermanagement.user.domain.User;
import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;

@Builder
public record UserUpdate(
        @NonNull
        UUID userUuid,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone
) {
        public static UserUpdate from(UUID userUuid, UserUpdateRequest request) {
                return UserUpdate.builder()
                        .userUuid(userUuid)
                        .username(request.username())
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .email(request.email())
                        .phone(request.phone())
                        .build();
        }
}
