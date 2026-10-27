package com.skedio.administrationservice.scopemanagement.scope.domain.argument;

import com.skedio.administrationservice.scopemanagement.scope.controller.models.ScopeCreateRequest;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record ScopeCreate(
        @NonNull
        String scopeName,
        String description,
        Boolean enabled
) {
        public static ScopeCreate from(ScopeCreateRequest request) {
                String scopeName = request.scopeName();
                String description = request.description();
                Boolean enabled = request.enabled() != null ? request.enabled() : true;
                return ScopeCreate.builder()
                        .scopeName(scopeName)
                        .description(description)
                        .enabled(enabled)
                        .build();
        }
}
