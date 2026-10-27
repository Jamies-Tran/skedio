package com.skedio.administrationservice.scopemanagement.scopeclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

interface ScopeClientJpaRepository extends JpaRepository<ScopeClientJpaEntity, Long> {
    @Modifying
    void deleteByClientUuidAndScopeUuid(UUID clientUuid, UUID scopeUuid);
}
