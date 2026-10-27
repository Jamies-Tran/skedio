package com.skedio.administrationservice.usermanagement.user.domain.argument;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserId(
        Long userId
) {}
