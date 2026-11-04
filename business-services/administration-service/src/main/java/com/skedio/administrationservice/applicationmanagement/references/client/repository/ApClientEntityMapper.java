package com.skedio.administrationservice.applicationmanagement.references.client.repository;

import com.skedio.administrationservice.applicationmanagement.references.client.domain.ApClient;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface ApClientEntityMapper extends EntityMapper<ApClientJpaEntity, ApClient> {
}
