package com.skedio.administrationservice.clientmanagement.references.clientscope.repository;

import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.ClClientScope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
interface ClClientScopeEntityMapper extends EntityMapper<ClClientScopeJpaEntity, ClClientScope> {
}
