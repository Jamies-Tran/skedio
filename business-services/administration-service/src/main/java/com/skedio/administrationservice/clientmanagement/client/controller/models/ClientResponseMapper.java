package com.skedio.administrationservice.clientmanagement.client.controller.models;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ClientResponseMapper extends DomainMapper<Client, ClientResponse> {
}
