package com.skedio.administrationservice.applicationmanagement.applications.domain.argument;

import com.skedio.administrationservice.applicationmanagement.applications.controller.models.ApplicationGroupRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record ApplicationGroupCreate(
        String applicationGroupName,
        String avatar,
        String description,
        Boolean enabled
) {
    public static List<ApplicationGroupCreate> from(List<ApplicationGroupRequest> requests) {
        return requests.stream()
                .map(request -> ApplicationGroupCreate.builder()
                        .applicationGroupName(request.applicationGroupName())
                        .avatar(request.avatar())
                        .description(request.description())
                        .enabled(request.enabled())
                        .build())
                .toList();
    }
}
