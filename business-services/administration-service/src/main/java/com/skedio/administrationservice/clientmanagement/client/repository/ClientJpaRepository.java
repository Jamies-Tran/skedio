package com.skedio.administrationservice.clientmanagement.client.repository;

import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface ClientJpaRepository extends JpaRepository<ClientJpaEntity, Long> {
    Boolean existsByClientNameIn(List<String> clientNames);

    Boolean existsByClientName(String clientName);

    Optional<ClientJpaEntity> findByClientUuid(UUID uuid);

    @Modifying
    void deleteByClientUuid(UUID uuid);

    @Modifying
    void deleteAllByApplicationUuid(UUID applicationUuid);

    @Query("""
        SELECT COUNT(a) > 0
        FROM ClApplication a
        WHERE a.applicationUuid = :applicationUuid
                AND a.enabled = TRUE
        """)
    Boolean existsApplicationByApplicationUuidAndEnabled(UUID applicationUuid);

    @Query("""
        SELECT COUNT(cs) > 0
        FROM ClientJpaEntity c
        INNER JOIN ClClientScope cs ON c.clientUuid = cs.clientUuid
        WHERE c.clientUuid = :clientUuid
        """)
    Boolean existsScopeInClient(UUID clientUuid);

    @Query("""
        SELECT COUNT(cs) > 0
        FROM ClientJpaEntity c
        INNER JOIN ClClientScope cs ON cs.clientUuid = c.clientUuid
        WHERE c.applicationUuid = :applicationUuid
        """)
    Boolean existsScopeInClientByApplicationUuid(UUID applicationUuid);

    @Query("""
        SELECT c
        FROM ClientJpaEntity c
        WHERE c.applicationUuid = :#{#search.applicationUuid()}
            AND (:#{#search.searchEmpty()} = TRUE
                    OR c.search LIKE %:#{#search.search()}%)
            AND (:#{#search.enabledEmpty()} = TRUE
                    OR c.enabled = :#{#search.enabled()})
           AND (:#{#search.timeRangeEmpty()} = TRUE
                   OR c.createdAt BETWEEN :#{#search.timeRangeFirst()} AND :#{#search.timeRangeLast()})
        """)
    Page<ClientJpaEntity> findAll(ClientSearch search, Pageable pageable);
}
