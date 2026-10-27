package com.skedio.administrationservice.clientmanagement.clientuser.domain.argument;

import com.skedio.administrationservice.clientmanagement.clientuser.controller.models.ClientUserListRequest;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;

import java.util.List;
import java.util.UUID;

public record ClientUserAssign(
        UUID clientUuid,
        List<UUID> userUuids
) {
    public static ClientUserAssign from(UUID clientUuid, ClientUserListRequest request) {
        List<UUID> userUuids = request.userUuids();
        return new ClientUserAssign(clientUuid, userUuids);
    }

    public List<ClientUser> createList() {
        return userUuids.stream()
                .map(userUuid -> ClientUser.builder()
                        .userUuid(userUuid)
                        .clientUuid(clientUuid)
                        .build())
                .toList();
    }
}
