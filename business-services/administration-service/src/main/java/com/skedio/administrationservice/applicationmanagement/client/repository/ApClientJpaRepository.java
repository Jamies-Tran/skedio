package com.skedio.administrationservice.applicationmanagement.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface ApClientJpaRepository extends JpaRepository<ApClientJpaEntity, Long> {
    @Modifying
    void deleteAllByApplicationUuid(UUID applicationUuid);

    @Query("""
        SELECT COUNT(a) > 0
        FROM ApApplication a
        WHERE a.applicationUuid = :applicationUuid
        """)
    Boolean existApplicationByApplicationUuid(UUID applicationUuid);
}
