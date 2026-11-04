package com.skedio.administrationservice.applicationmanagement.references.applicationuser.controller.models;

import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.ApApplicationUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ApplicationGroupUserResponseMapper extends DomainMapper<ApplicationGroupUserResponse, ApApplicationUser> {
}
