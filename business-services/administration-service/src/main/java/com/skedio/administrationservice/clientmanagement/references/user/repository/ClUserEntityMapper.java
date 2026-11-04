package com.skedio.administrationservice.clientmanagement.references.user.repository;

import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.ClClientUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(
        implementationName = "CUUserEntityMapper",
        config = GlobalMapStructConfig.class
)
public interface ClUserEntityMapper extends EntityMapper<ClUserJpaEntity, ClClientUser> {
}
