package com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.argument;

import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.ApApplicationUser;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public record ApplicationUserAddList(
        @NonNull
        UUID applicationUuid,
        @NonNull
        List<UUID> userUuids
) {
    public List<ApApplicationUser> computeSaveList() {
        return userUuids.stream()
                .map(userUuid -> ApApplicationUser.from(userUuid, applicationUuid))
                .toList();
    }

    public List<ApApplicationUser> computeSaveListExcludeUserUuid(List<UUID> existUserUuids) {
        return userUuids.stream()
                .filter(userUuid -> !existUserUuids.contains(userUuid))
                .map(userUuid -> ApApplicationUser.from(userUuid, applicationUuid))
                .toList();
    }
}
