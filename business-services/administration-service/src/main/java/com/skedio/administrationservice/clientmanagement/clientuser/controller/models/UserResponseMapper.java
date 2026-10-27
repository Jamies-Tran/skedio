package com.skedio.administrationservice.clientmanagement.clientuser.controller.models;

import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(
        implementationName = "CUUserResponseMapper",
        config = GlobalMapStructConfig.class
)
public interface UserResponseMapper extends DomainMapper<ClientUser, UserResponse> {
}
