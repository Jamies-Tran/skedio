package com.skedio.administrationservice.clientmanagement.client.domain.argument;

import com.skedio.administrationservice.clientmanagement.client.controller.models.ClientCreateRequest;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Builder
public record ClientCreateList(
        @NonNull
        UUID applicationUuid,
        @NonNull
        List<ClientCreate> creates
) {
        public static ClientCreateList from(UUID applicationUuid, List<ClientCreateRequest> requests) {
                List<ClientCreate> creates = ClientCreate.from(requests);
                return ClientCreateList.builder()
                        .applicationUuid(applicationUuid)
                        .creates(creates)
                        .build();
        }
}
