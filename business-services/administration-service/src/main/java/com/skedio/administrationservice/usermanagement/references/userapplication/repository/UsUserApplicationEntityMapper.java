package com.skedio.administrationservice.usermanagement.references.userapplication.repository;

import com.skedio.administrationservice.usermanagement.references.userapplication.domain.UsUserApplication;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface UsUserApplicationEntityMapper extends EntityMapper<UsUserApplicationJpaEntity, UsUserApplication> {
}
