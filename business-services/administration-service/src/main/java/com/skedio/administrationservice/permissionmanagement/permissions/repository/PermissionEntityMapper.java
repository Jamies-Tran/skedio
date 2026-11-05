package com.skedio.administrationservice.permissionmanagement.permissions.repository;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.Permission;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface PermissionEntityMapper extends EntityMapper<PermissionJpaEntity, Permission> {
}
