package com.skedio.administrationservice.usermanagement.user.repository;

import com.skedio.administrationservice.usermanagement.user.domain.argument.UserSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<UserJpaEntity> findByUserUuid(UUID userUuid);

    Optional<UserJpaEntity> findByUserId(Long userId);

    @Query("""
        SELECT u
        FROM UserJpaEntity u
        WHERE  (:#{#userSearch.searchEmpty()} = true
                        OR u.search LIKE %:#{#userSearch.search()}%)
                AND (:#{#userSearch.statusCodesEmpty()} = true
                        OR u.statusCode IN :#{#userSearch.statusCodes()})
                AND (:#{#userSearch.timeRangeEmpty()} = true
                        OR u.createdAt BETWEEN :#{#userSearch.timeRangeFirst()} AND :#{#userSearch.timeRangeLast()})
        """)
    Page<UserJpaEntity> findAll(UserSearch userSearch, Pageable pageable);
}
