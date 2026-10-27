package com.skedio.administrationservice.scopemanagement.scope.domain;

import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeCreate;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeUpdate;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Scope(
        Long scopeId,
        UUID scopeUuid,
        @With
        String scopeName,
        @With
        String description,
        @With
        Boolean enabled,
        @With
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public Scope computeBeforeSave() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.scopeName);
        Boolean enabled = this.enabled != null ? this.enabled : true;
        return this
                .withSearch(search)
                .withEnabled(enabled);
    }

    public static Scope from(ScopeCreate create) {
        String scopeName = create.scopeName();
        String description = create.description();
        Boolean enabled = create.enabled();
        return Scope.builder()
                .scopeName(scopeName)
                .description(description)
                .enabled(enabled)
                .build();
    }

    public Scope enable() {
        Boolean enabled = true;
        return this.withEnabled(enabled);
    }

    public Scope disable() {
        Boolean enabled = false;
        return this.withEnabled(enabled);
    }

    public Scope apply(ScopeUpdate update) {
        String scopeName = update.scopeName() == null ? this.scopeName : update.scopeName();
        String description = update.description();
        Boolean enabled = update.enabled() == null ? this.enabled : update.enabled();
        return this
                .withScopeName(scopeName)
                .withDescription(description)
                .withEnabled(enabled);
    }
}
