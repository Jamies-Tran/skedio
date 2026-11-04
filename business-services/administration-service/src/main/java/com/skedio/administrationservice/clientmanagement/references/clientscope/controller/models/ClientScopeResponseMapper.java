package com.skedio.administrationservice.clientmanagement.references.clientscope.controller.models;

import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.ClClientScope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ClientScopeResponseMapper extends DomainMapper<ClClientScope, ClientScopeResponse> {
}
