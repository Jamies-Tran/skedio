package com.skedio.administrationservice.clientmanagement.clientscope.controller.models;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.ClientScope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ClientScopeResponseMapper extends DomainMapper<ClientScope, ClientScopeResponse> {
}
