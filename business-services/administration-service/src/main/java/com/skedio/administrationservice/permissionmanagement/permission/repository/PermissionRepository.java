package com.skedio.administrationservice.permissionmanagement.permission.repository;

import com.skedio.administrationservice.permissionmanagement.permission.domain.Permission;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionRepository {
    PermissionJpaRepository repository;
    PermissionEntityMapper entityMapper;

    public Boolean existsByPermissionName(String permissionName) {
        return repository.existsByPermissionName(permissionName);
    }

    public Permission save(Permission permission) {
        PermissionJpaEntity entity = entityMapper.toEntity(permission);
        PermissionJpaEntity saved = repository.save(entity);
        return entityMapper.toDomain(saved);
    }
}
