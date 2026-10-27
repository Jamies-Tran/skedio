package com.skedio.administrationservice.clientmanagement.clientscope.repository;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

interface ClientScopeJpaRepository extends JpaRepository<ClientScopeJpaEntity, Long> {
    Boolean existsByClientUuidAndScopeUuidIn(UUID clientUuid, List<UUID> scopeUuids);

    @Modifying
    void deleteAllByClientUuid(UUID clientUuid);

    @Query("""
        SELECT COUNT(c) > 0
        FROM CSClient  c
        WHERE c.clientUuid = :clientUuid
                AND c.enabled = TRUE
        """)
    Boolean existsClientByClientUuidAndEnabledTrue(UUID clientUuid);

    @Query("""
        SELECT COUNT(s) > 0
        FROM ClScope s
        WHERE s.scopeUuid = :scopeUuid
        """)
    Boolean existsScopeByScopeUuid(UUID scopeUuid);

    @Query("""
        SELECT
                cs.clientScopeId AS clientScopeId,
                c.clientUuid AS clientUuid,
                s.scopeUuid AS scopeUuid,
                s.scopeName AS scopeName,
                cs.resourcePath AS resourcePath
        FROM ClientScopeJpaEntity cs
        INNER JOIN ClScope s ON cs.scopeUuid = s.scopeUuid
        INNER JOIN CSClient c ON c.clientUuid = cs.clientUuid
        WHERE c.clientUuid = :#{#search.clientUuid()}
            AND (:#{#search.searchEmpty()} = TRUE
                OR s.search LIKE %:#{#search.search()}%)
        """)
    Page<ClientScopeDao> findAllDao(ClientScopeSearch search, Pageable pageable);
}
