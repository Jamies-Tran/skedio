package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import jakarta.validation.Valid;

import java.util.List;

public record ApplicationGroupRequestList(
        @Valid
        List<ApplicationGroupRequest> applicationGroups
) {
}
