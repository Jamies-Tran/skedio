package com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument;

import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.ApplicationUser;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public record ApplicationUserAddList(
        @NonNull
        UUID applicationUuid,
        @NonNull
        List<UUID> userUuids
) {
    public List<ApplicationUser> computeSaveList() {
        return userUuids.stream()
                .map(userUuid -> ApplicationUser.from(userUuid, applicationUuid))
                .toList();
    }

    public List<ApplicationUser> computeSaveListExcludeUserUuid(List<UUID> existUserUuids) {
        return userUuids.stream()
                .filter(userUuid -> !existUserUuids.contains(userUuid))
                .map(userUuid -> ApplicationUser.from(userUuid, applicationUuid))
                .toList();
    }
}
