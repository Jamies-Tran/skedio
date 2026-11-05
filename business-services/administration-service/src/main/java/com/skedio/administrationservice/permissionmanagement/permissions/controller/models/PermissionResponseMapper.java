package com.skedio.administrationservice.permissionmanagement.permissions.controller.models;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.Permission;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface PermissionResponseMapper extends DomainMapper<Permission, PermissionResponse> {
}
