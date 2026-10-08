package com.skedio.administrationservice.usermanagement.user.controller.models;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
public interface UserRequestMapper extends DomainMapper<User, UserRequest> {
}
