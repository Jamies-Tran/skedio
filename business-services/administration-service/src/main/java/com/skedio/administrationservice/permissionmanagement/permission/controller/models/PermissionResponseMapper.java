package com.skedio.administrationservice.permissionmanagement.permission.controller.models;

import com.skedio.administrationservice.permissionmanagement.permission.domain.Permission;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface PermissionResponseMapper extends DomainMapper<Permission, PermissionResponse> {
}
