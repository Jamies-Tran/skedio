package com.skedio.administrationservice.clientmanagement.clientuser.controller.models;

import java.util.UUID;

public record UserResponse(
        UUID userUuid,
        String username,
        String firstName,
        String lastName,
        String phone,
        String mail
) {
}
