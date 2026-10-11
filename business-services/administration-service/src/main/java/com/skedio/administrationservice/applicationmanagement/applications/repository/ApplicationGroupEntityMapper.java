package com.skedio.administrationservice.applicationmanagement.applications.repository;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ApplicationGroupEntityMapper extends EntityMapper<ApplicationGroupJpaEntity, ApplicationGroup> {
}
