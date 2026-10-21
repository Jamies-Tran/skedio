package com.skedio.administrationservice.applicationmanagement.applications.domain;

import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupCreate;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupUpdate;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ApplicationGroup(
        Long applicationId,
        UUID applicationUuid,
        @With
        String applicationName,
        @With
        String avatar,
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
    public static List<ApplicationGroup> from(List<ApplicationGroupCreate> creates) {
        return creates.stream()
                .map(create -> ApplicationGroup.builder()
                        .applicationName(create.applicationGroupName())
                        .avatar(create.avatar())
                        .description(create.description())
                        .enabled(create.enabled())
                        .build())
                .toList();
    }

    public ApplicationGroup computeBeforeSave() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.applicationName);
        Boolean enabled = this.enabled != null ? this.enabled : true;
        return this
                .withEnabled(enabled)
                .withSearch(search);
    }

    public ApplicationGroup apply(ApplicationGroupUpdate update) {
        String applicationName = StringUtils.hasText(update.applicationName()) ? update.applicationName() : this.applicationName;
        String avatar = StringUtils.hasText(update.avatar()) ? update.avatar() : this.avatar;
        String description = StringUtils.hasText(update.description()) ? update.description() : this.description;
        Boolean enabled = update.enabled() != null ? update.enabled() : this.enabled;
        return this
                .withApplicationName(applicationName)
                .withAvatar(avatar)
                .withDescription(description)
                .withEnabled(enabled);
    }

    public ApplicationGroup disable() {
        Boolean enabled = Boolean.FALSE;
        return this
                .withEnabled(enabled);
    }

    public ApplicationGroup enable() {
        Boolean enabled = Boolean.TRUE;
        return this
                .withEnabled(enabled);
    }
}
