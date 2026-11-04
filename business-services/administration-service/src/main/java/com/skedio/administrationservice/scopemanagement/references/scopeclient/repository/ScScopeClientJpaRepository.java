package com.skedio.administrationservice.scopemanagement.references.scopeclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

interface ScScopeClientJpaRepository extends JpaRepository<ScScopeClientJpaEntity, Long> {
    @Modifying
    void deleteByClientUuidAndScopeUuid(UUID clientUuid, UUID scopeUuid);
}
