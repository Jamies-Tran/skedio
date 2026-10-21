package com.skedio.administrationservice.clientmanagement.client.domain;

import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientCreate;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientUpdate;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record Client(
        Long clientId,
        UUID applicationUuid,
        UUID clientUuid,
        @With
        String clientName,
        @With
        String avatar,
        @With
        Boolean enabled,
        @With
        String description,
        @With
        String search,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static List<Client> from(UUID applicationUuid, List<ClientCreate> creates) {
        return creates.stream()
                .map(create -> Client.builder()
                        .applicationUuid(applicationUuid)
                        .clientName(create.clientName())
                        .avatar(create.avatar())
                        .description(create.description())
                        .enabled(create.enabled())
                        .build())
                .toList();
    }

    public Client computeBeforeSave() {
        Boolean enabled = this.enabled != null ? this.enabled : true;
        String search = StringConvertUtils.normalizeWhiteSpace(this.clientName);
        return this
                .withSearch(search)
                .withEnabled(enabled);
    }

    public Client apply(ClientUpdate update) {
        String newClientName = StringUtils.hasText(update.clientName()) ? update.clientName() : this.clientName;
        String newAvatar = StringUtils.hasText(update.avatar()) ? update.avatar() : this.avatar;
        String newDescription = StringUtils.hasText(update.description()) ? update.description() : this.description;
        Boolean newEnabled = update.enabled() != null ? update.enabled() : this.enabled;
        return this
                .withClientName(newClientName)
                .withAvatar(newAvatar)
                .withDescription(newDescription)
                .withEnabled(newEnabled);
    }

    public Client enable() {
        Boolean enabled = true;
        return this.withEnabled(enabled);
    }

    public Client disable() {
        Boolean enabled = false;
        return this.withEnabled(enabled);
    }
}
