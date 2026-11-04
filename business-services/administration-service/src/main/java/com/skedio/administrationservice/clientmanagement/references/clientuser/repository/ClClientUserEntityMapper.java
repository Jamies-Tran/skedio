package com.skedio.administrationservice.clientmanagement.references.clientuser.repository;

import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.ClClientUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
interface ClClientUserEntityMapper extends EntityMapper<ClClientUserJpaEntity, ClClientUser> {
}
