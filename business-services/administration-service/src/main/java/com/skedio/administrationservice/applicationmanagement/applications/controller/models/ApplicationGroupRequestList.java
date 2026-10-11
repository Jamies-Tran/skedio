package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ApplicationGroupNameList(
        @NotNull(message = "Application name must not null")
        @Size(min = 1,  message = "Must have at least one element")
        List<String> applicationNames
) {
}
