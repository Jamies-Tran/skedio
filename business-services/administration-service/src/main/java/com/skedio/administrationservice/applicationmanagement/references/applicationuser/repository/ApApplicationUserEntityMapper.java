package com.skedio.administrationservice.applicationmanagement.references.applicationuser.repository;

import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.ApApplicationUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ApApplicationUserEntityMapper extends EntityMapper<ApApplicationUserJpaEntity, ApApplicationUser> {
}
