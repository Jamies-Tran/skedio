package com.skedio.administrationservice.applicationmanagement.applications.repository;

import com.skedio.administrationservice.applicationmanagement.applications.domain.Client;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(
        implementationName = "AppClientEntityMapper",
        config = GlobalMapStructConfig.class
)
public interface ClientEntityMapper extends EntityMapper<ClientJpaEntity, Client> {
}
