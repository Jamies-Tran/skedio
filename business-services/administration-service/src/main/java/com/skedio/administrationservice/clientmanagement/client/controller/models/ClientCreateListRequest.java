package com.skedio.administrationservice.clientmanagement.client.controller.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientCreateListRequest(
        @NotNull(message = "List must  not be null")
        @Size(min = 1, message = "List must have at least one element")
        List<@Valid ClientCreateRequest> createList
) {
}
