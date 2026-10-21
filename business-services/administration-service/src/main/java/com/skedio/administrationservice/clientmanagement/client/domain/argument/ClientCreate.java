package com.skedio.administrationservice.clientmanagement.client.domain.argument;

import com.skedio.administrationservice.clientmanagement.client.controller.models.ClientCreateRequest;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record ClientCreate(
        @NonNull
        String clientName,
        String avatar,
        String description,
        Boolean enabled
) {
        public static List<ClientCreate> from(List<ClientCreateRequest> requests) {
                return requests.stream()
                        .map(request -> ClientCreate.builder()
                                .clientName(request.clientName())
                                .avatar(request.avatar())
                                .description(request.description())
                                .enabled(request.enabled())
                                .build())
                        .toList();
        }
}
