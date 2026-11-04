package com.skedio.administrationservice.scopemanagement.references.scopeclient.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScScopeClientRepository {
    ScScopeClientJpaRepository repository;

    public void deleteByClientUuidAndScopeUuid(UUID clientUuid, UUID scopeUuid) {
        repository.deleteByClientUuidAndScopeUuid(clientUuid, scopeUuid);
    }
}
