package com.skedio.administrationservice.applicationmanagement.applicationuser.repository;

import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.ApplicationUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ApplicationUserEntityMapper extends EntityMapper<ApplicationUserJpaEntity, ApplicationUser> {
}
