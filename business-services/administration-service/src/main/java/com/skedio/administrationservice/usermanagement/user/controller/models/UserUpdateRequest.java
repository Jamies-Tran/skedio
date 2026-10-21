package com.skedio.administrationservice.usermanagement.user.controller.models;

public record UserUpdateRequest(
    String username,
    String firstName,
    String lastName,
    String email,
    String phone,
    Boolean enabled
) {
}
