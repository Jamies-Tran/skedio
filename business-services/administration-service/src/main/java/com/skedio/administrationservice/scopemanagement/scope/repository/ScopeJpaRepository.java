package com.skedio.administrationservice.scopemanagement.scope.repository;

import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

interface ScopeJpaRepository extends JpaRepository<ScopeJpaEntity, Long> {
    Boolean existsByScopeName(String scopeName);

    Optional<ScopeJpaEntity> findByScopeUuid(UUID scopeUuid);

    @Modifying
    void deleteByScopeUuid(UUID scopeUuid);

    @Query("""
        SELECT COUNT(cs) > 0
        FROM ScopeJpaEntity s
        INNER JOIN ScClientScope cs ON s.scopeUuid = cs.scopeUuid
        WHERE cs.scopeUuid = :scopeUuid
        """)
    Boolean existsScopeInClient(UUID scopeUuid);

    @Query("""
        SELECT s
        FROM ScopeJpaEntity s
        WHERE (:#{#search.searchEmpty()} = TRUE
                OR s.search LIKE %:#{#search.search()}%)
        AND (:#{#search.timeRangeEmpty()} = TRUE
                OR s.createdAt BETWEEN :#{#search.timeRangeFirst()} AND :#{#search.timeRangeLast()})
        """)
    Page<ScopeJpaEntity> findAll(ScopeSearch search, Pageable pageable);
}
