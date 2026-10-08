package com.skedio.administrationservice.usermanagement.user.repository;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.corestarter.configuration.GlobalMapStructConfig;
import com.skedio.corestarter.template.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapStructConfig.class)
interface UserEntityMapper extends EntityMapper<UserEntity, User> {
}
