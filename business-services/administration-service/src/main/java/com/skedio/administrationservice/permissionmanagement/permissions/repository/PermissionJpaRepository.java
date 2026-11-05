package com.skedio.administrationservice.permissionmanagement.permissions.repository;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

interface PermissionJpaRepository extends JpaRepository<PermissionJpaEntity, Long> {
    Boolean existsByPermissionName(String permissionName);

    Optional<PermissionJpaEntity> findByPermissionUuid(UUID permissionUuid);

    @Query("""
        SELECT p
        FROM PermissionJpaEntity p
        WHERE (:#{#search.enabledEmpty()} = TRUE
                OR p.enabled = :#{#search.enabled()})
             AND (:#{#search.searchEmpty()} = TRUE
                      OR p.search LIKE %:#{#search.search()}%)
             AND (:#{#search.timeRangeEmpty()} = TRUE
                     OR p.createdAt BETWEEN :#{#search.timeRangeFirst()}
                        AND :#{#search.timeRangeLast()})
        """)
    Page<PermissionJpaEntity> findAll(PermissionSearch search, Pageable pageable);

    @Modifying
    void deleteByPermissionUuid(UUID permissionuuid);
}
