package com.skedio.administrationservice.clientmanagement.clientscope.repository;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.ClientScope;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DaoMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ClientScopeDaoMapper extends DaoMapper<ClientScopeDao, ClientScope> {
}
