package com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument;

import com.skedio.administrationservice.clientmanagement.references.clientuser.controller.models.ClientUserListRequest;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.ClClientUser;

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

    public List<ClClientUser> createList() {
        return userUuids.stream()
                .map(userUuid -> ClClientUser.builder()
                        .userUuid(userUuid)
                        .clientUuid(clientUuid)
                        .build())
                .toList();
    }
}
