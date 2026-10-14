package com.skedio.administrationservice.usermanagement.userapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface UserApplicationJpaRepository extends JpaRepository<UserApplicationJpaEntity, Long> {
    @Modifying
    void deleteByUserUuidAndApplicationUuid(UUID userUuid, UUID applicationUuid);

    Boolean existsByUserUuidAndApplicationUuid(UUID userUuid, UUID applicationUuid);

    @Query("""
        SELECT COUNT(a) > 0
        FROM UaApplicationGroup a
        WHERE a.applicationUuid = :applicationUuid
        """)
    Boolean existsApplicationByApplicationUuid(UUID applicationUuid);

    @Query("""
        SELECT COUNT(u) > 0
        FROM UaUser u
        WHERE u.userUuid = :userUuid
        """)
    Boolean existsUserByUserUuid(UUID userUuid);
}
