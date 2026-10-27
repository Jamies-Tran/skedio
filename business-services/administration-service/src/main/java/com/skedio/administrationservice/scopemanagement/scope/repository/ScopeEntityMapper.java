package com.skedio.administrationservice.scopemanagement.scope.repository;

import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
interface ScopeEntityMapper extends EntityMapper<ScopeJpaEntity, Scope> {
}
