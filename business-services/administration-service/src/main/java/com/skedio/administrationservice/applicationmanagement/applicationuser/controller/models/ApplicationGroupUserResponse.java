package com.skedio.administrationservice.applicationmanagement.applicationuser.controller.models;

import java.util.UUID;

public record ApplicationGroupUserResponse(
        Long userApplicationId,
        UUID userUuid,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
