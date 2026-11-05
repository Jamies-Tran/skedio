package com.skedio.administrationservice.permissionmanagement.permissions.repository;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.Permission;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionSearch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionRepository {
    PermissionJpaRepository repository;
    PermissionEntityMapper entityMapper;

    public Boolean existsByPermissionName(String permissionName) {
        return repository.existsByPermissionName(permissionName);
    }

    public Optional<Permission> findByPermissionUuid(UUID permissionUuid) {
        return repository.findByPermissionUuid(permissionUuid)
                .map(entityMapper::toDomain);
    }

    public Permission save(Permission permission) {
        PermissionJpaEntity entity = entityMapper.toEntity(permission);
        PermissionJpaEntity saved = repository.save(entity);
        return entityMapper.toDomain(saved);
    }

    public Page<Permission> findAll(PermissionSearch search, PageRequest pageRequest) {
        return repository.findAll(search, pageRequest)
                .map(entityMapper::toDomain);
    }

    public void deleteByPermissionUuid(UUID permissionUuid) {
        repository.deleteByPermissionUuid(permissionUuid);
    }
}
