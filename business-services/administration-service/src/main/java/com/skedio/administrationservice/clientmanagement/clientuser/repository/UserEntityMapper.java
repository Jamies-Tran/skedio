package com.skedio.administrationservice.clientmanagement.clientuser.repository;

import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(
        implementationName = "CUUserEntityMapper",
        config = GlobalMapStructConfig.class
)
public interface UserEntityMapper extends EntityMapper<UserJpaEntity, ClientUser> {
}
