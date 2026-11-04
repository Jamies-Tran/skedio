package com.skedio.administrationservice.clientmanagement.references.clientuser.repository;

import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument.ClientUserSearch;
import com.skedio.administrationservice.clientmanagement.references.user.repository.ClUserJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

interface ClClientUserJpaRepository extends JpaRepository<ClClientUserJpaEntity, Long> {
    Boolean existsByClientUuidAndUserUuidIn(UUID clientUuid, List<UUID> userUuIds);

    @Modifying
    void deleteAllByClientUuid(UUID clientUuid);

    @Query("""
        SELECT COUNT(c) > 0
        FROM ClientJpaEntity c
        WHERE c.clientUuid = :clientUuid
                AND c.enabled = TRUE
        """)
    Boolean existsClientByClientUuidAndEnabledTrue(UUID clientUuid);

    @Query("""
        SELECT COUNT(c) > 0
        FROM ClientJpaEntity c
        WHERE c.clientUuid = :clientUuid
        """)
    Boolean existsClientByClientUuid(UUID clientUuid);

    @Query("""
        SELECT u
        FROM ClUser u
        INNER JOIN ClClientUserJpaEntity cu ON cu.userUuid = u.userUuid
        WHERE cu.clientUuid = :#{#search.clientUuid()}
            AND (:#{#search.searchEmpty()} = TRUE
                    OR u.search LIKE %:#{#search.search()}%)
        """)
    Page<ClUserJpaEntity> findAllUserInClient(ClientUserSearch search, Pageable pageable);
}
