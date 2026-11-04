package com.skedio.administrationservice.permissionmanagement.permission.repository;

import com.skedio.administrationservice.permissionmanagement.permission.domain.Permission;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface PermissionEntityMapper extends EntityMapper<PermissionJpaEntity, Permission> {
}
