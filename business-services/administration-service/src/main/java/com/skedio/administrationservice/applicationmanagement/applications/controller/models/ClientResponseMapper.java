package com.skedio.administrationservice.applicationmanagement.applications.controller.models;

import com.skedio.administrationservice.applicationmanagement.references.client.domain.ApClient;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(
        implementationName = "AppClientResponseMapper",
        config = GlobalMapStructConfig.class
)
public interface ClientResponseMapper extends DomainMapper<ApClient, ClientResponse> {
}
