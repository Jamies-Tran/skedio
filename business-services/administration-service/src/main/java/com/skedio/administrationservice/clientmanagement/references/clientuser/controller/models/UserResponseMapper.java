package com.skedio.administrationservice.clientmanagement.references.clientuser.controller.models;

import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.ClClientUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(
        implementationName = "CUUserResponseMapper",
        config = GlobalMapStructConfig.class
)
public interface UserResponseMapper extends DomainMapper<ClClientUser, UserResponse> {
}
