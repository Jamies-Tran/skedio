package com.skedio.administrationservice.clientmanagement.references.clientscope.repository;

import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.ClClientScope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DaoMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ClClientScopeDaoMapper extends DaoMapper<ClClientScopeDao, ClClientScope> {
}
