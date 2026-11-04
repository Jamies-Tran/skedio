package com.skedio.administrationservice.clientmanagement.references.clientscope.repository;

import java.util.UUID;

public interface ClClientScopeDao {
    Long getClientScopeId();

    UUID getClientUuid();

    UUID getScopeUuid();

    String getScopeName();

    String getResourcePath();
}
