package com.skedio.administrationservice.scopemanagement.scopeclient.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeClientRepository {
    ScopeClientJpaRepository repository;

    public void deleteByClientUuidAndScopeUuid(UUID clientUuid, UUID scopeUuid) {
        repository.deleteByClientUuidAndScopeUuid(clientUuid, scopeUuid);
    }
}
