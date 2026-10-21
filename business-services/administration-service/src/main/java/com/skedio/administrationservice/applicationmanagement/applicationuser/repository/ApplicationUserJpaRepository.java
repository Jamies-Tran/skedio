package com.skedio.administrationservice.applicationmanagement.applicationuser.repository;

import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument.ApplicationGroupGetUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ApplicationUserJpaRepository extends JpaRepository<ApplicationUserJpaEntity, Long> {
    @Query("""
        SELECT
                ua.userApplicationId AS userApplicationId,
                u.userUuid AS userUuid,
                u.firstName AS firstName,
                u.lastName AS lastName,
                u.email AS email,
                u.phone AS phone
        FROM UserApplicationJpaEntity ua
        INNER JOIN UserJpaEntity u ON ua.userUuid = u.userUuid
        WHERE (:#{#search.searchEmpty()} = TRUE
                OR u.search LIKE %:#{#search.search()}%)
        AND ua.applicationUuid = :#{#search.applicationUuid()}
        """)
    Page<ApplicationUserDao> findUserDaoInApplication(ApplicationGroupGetUser search, Pageable pageable);

    @Modifying
    void deleteAllByApplicationUuid(UUID applicationUuid);

    @Query("""
        SELECT COUNT(u) > 0
        FROM AuUser u
        WHERE u.userUuid = :userUuid
        """)
    Boolean existsUserByUserUuid(UUID userUuid);

    @Query("""
        SELECT COUNT(a) > 0
        FROM AuApplication a
        WHERE a.applicationUuid = :applicationUuid
                AND a.enabled = TRUE
        """)
    Boolean existsApplicationByApplicationUuidAndEnabled(UUID applicationUuid);

    @Query("""
        SELECT au.userUuid
        FROM ApplicationUserJpaEntity au
        WHERE au.applicationUuid = :applicationUuid
        """)
    List<UUID> findAllUserUuidByApplicationUuid(UUID applicationUuid);

    @Query("""
        SELECT COUNT(au) > 0
        FROM ApplicationUserJpaEntity au
        INNER JOIN UserJpaEntity u ON au.userUuid = u.userUuid
        WHERE au.applicationUuid = :applicationUuid
        """)
    Boolean existUserInApplication(UUID applicationUuid);
}
