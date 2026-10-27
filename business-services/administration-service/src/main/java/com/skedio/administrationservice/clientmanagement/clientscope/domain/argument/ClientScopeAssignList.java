package com.skedio.administrationservice.clientmanagement.clientscope.domain.argument;

import com.skedio.administrationservice.clientmanagement.clientscope.controller.models.ClientScopeAssignRequest;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ClientScopeAssignList(
        UUID clientUuid,
        List<ClientScopeAssign> assignList
) {
    public static ClientScopeAssignList from(UUID clientUuid, List<ClientScopeAssignRequest> assignRequests) {
        List<ClientScopeAssign> assignList = ClientScopeAssign.from(assignRequests);
        return ClientScopeAssignList.builder()
                .clientUuid(clientUuid)
                .assignList(assignList)
                .build();
    }
}
