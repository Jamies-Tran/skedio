package com.skedio.administrationservice.clientmanagement.clientscope.repository;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.ClientScope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
interface ClientScopeEntityMapper extends EntityMapper<ClientScopeJpaEntity, ClientScope> {
}
