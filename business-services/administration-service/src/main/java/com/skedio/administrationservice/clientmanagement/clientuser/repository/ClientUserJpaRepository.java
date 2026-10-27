package com.skedio.administrationservice.clientmanagement.clientuser.repository;

import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.argument.ClientUserSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

interface ClientUserJpaRepository extends JpaRepository<ClientUserJpaEntity, Long> {
    Boolean existsByClientUuidAndUserUuidIn(UUID clientUuid, List<UUID> userUuIds);

    @Query("""
        SELECT COUNT(c) > 0
        FROM CSClient c
        WHERE c.clientUuid = :clientUuid
                AND c.enabled = TRUE
        """)
    Boolean existsClientByClientUuidAndEnabledTrue(UUID clientUuid);

    @Query("""
        SELECT u
        FROM ClientUserJpaEntity cu
        INNER JOIN CUUser u ON cu.userUuid = u.userUuid
        WHERE cu.clientUuid = :#{#search.clientUuid()}
            AND (:#{#search.searchEmpty()} = TRUE
                    OR u.search LIKE %:#{#search.search()}%)
        """)
    Page<UserJpaEntity> findAllUserInClient(ClientUserSearch search, Pageable pageable);
}
