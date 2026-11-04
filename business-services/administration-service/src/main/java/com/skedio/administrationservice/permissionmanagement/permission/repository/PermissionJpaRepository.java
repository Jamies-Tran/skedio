package com.skedio.administrationservice.permissionmanagement.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

interface PermissionJpaRepository extends JpaRepository<PermissionJpaEntity, Long> {
    Boolean existsByPermissionName(String permissionName);
}
