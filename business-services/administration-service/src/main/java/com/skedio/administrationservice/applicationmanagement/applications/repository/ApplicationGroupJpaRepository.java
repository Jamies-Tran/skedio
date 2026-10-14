package com.skedio.administrationservice.applicationmanagement.applications.repository;

import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface ApplicationGroupJpaRepository extends JpaRepository<ApplicationGroupJpaEntity, Long> {
    Boolean existsByApplicationNameIn(List<String> applicationGroupNames);

    Boolean existsByApplicationName(String applicationName);

    Optional<ApplicationGroupJpaEntity> findByApplicationUuid(UUID applicationUuid);

    @Modifying
    void deleteByApplicationUuid(UUID applicationUuid);

    @Query("""
        SELECT ag
        FROM ApplicationGroupJpaEntity ag
        WHERE (:#{#search.searchEmpty()} = TRUE
                OR ag.search LIKE %:#{#search.search()}%)
        AND (:#{#search.enabledEmpty()} = TRUE
                OR ag.enabled = :#{#search.enabled()})
        AND (:#{#search.timeRangeEmpty()} = TRUE
                OR ag.createdAt BETWEEN :#{#search.timeRangeFirst()} 
                        AND :#{#search.timeRangeLast()})
        """)
    Page<ApplicationGroupJpaEntity> findAll(ApplicationGroupSearch search, Pageable pageable);
}
