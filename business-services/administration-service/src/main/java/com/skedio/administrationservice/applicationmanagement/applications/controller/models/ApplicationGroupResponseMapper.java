package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ApplicationGroupResponseMapper extends DomainMapper<ApplicationGroup, ApplicationGroupResponse> {
}
