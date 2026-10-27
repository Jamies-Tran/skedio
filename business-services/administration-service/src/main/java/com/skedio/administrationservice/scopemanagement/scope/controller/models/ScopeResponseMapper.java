package com.skedio.administrationservice.scopemanagement.scope.controller.models;

import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ScopeResponseMapper extends DomainMapper<ScopeResponse, Scope> {
}
