package com.skedio.administrationservice.usermanagement.userapplication.repository;

import com.skedio.administrationservice.usermanagement.userapplication.domain.UserApplication;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface UserApplicationEntityMapper extends EntityMapper<UserApplicationJpaEntity, UserApplication> {
}
