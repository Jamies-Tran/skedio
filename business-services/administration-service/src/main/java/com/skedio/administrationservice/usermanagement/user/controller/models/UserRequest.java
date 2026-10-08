package com.skedio.administrationservice.usermanagement.user.controller.models;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Username must not be null")
        String username,
        @NotBlank(message = "Password must not be null")
        String password,
        @NotBlank(message = "First name must not be null")
        String firstName,
        @NotBlank(message = "Last name must not be null")
        String lastName,
        @NotBlank(message = "Email must not be null")
        String email,
        String phone
) {
}
