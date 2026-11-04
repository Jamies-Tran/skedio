package com.skedio.administrationservice.usermanagement.references.userapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface UsUserApplicationJpaRepository extends JpaRepository<UsUserApplicationJpaEntity, Long> {
    @Modifying
    void deleteByUserUuidAndApplicationUuid(UUID userUuid, UUID applicationUuid);

    Boolean existsByUserUuidAndApplicationUuid(UUID userUuid, UUID applicationUuid);

    @Query("""
        SELECT COUNT(a) > 0
        FROM UsApplicationGroup a
        WHERE a.applicationUuid = :applicationUuid
                AND a.enabled = TRUE
        """)
    Boolean existsApplicationByApplicationUuidAndEnabled(UUID applicationUuid);

    @Query("""
        SELECT COUNT(u) > 0
        FROM UserJpaEntity u
        WHERE u.userUuid = :userUuid
        """)
    Boolean existsUserByUserUuid(UUID userUuid);
}
