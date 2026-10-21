package com.skedio.administrationservice.clientmanagement.client.repository;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ClientEntityMapper extends EntityMapper<ClientJpaEntity, Client> {
}
