package com.skedio.administrationservice.clientmanagement.clientscope.repository;

import java.util.UUID;

public interface ClientScopeDao {
    Long getClientScopeId();

    UUID getClientUuid();

    UUID getScopeUuid();

    String getScopeName();

    String getResourcePath();
}
