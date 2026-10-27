package com.skedio.administrationservice.clientmanagement.clientscope.service.command;

import com.skedio.administrationservice.clientmanagement.client.domain.error.EnumClientException;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.ClientScope;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeAssign;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeAssignList;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.error.EnumClientScopeException;
import com.skedio.administrationservice.clientmanagement.clientscope.repository.ClientScopeRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientScopeAssignListUseCase extends Command<ClientScopeAssignList, Void> {
    ClientScopeRepository clientScopes;

    @Override
    public Void handle(ClientScopeAssignList command) {
        UUID clientUuid = command.clientUuid();
        Boolean exists = clientScopes.existsClientByClientUuidAndEnabledTrue(clientUuid);
        if(!exists) {
            throw clientNotFoundDupInListException();
        }
        List<ClientScopeAssign> assigns = command.assignList();
        validateBeforeSave(clientUuid, assigns);
        List<ClientScope> clientScopeList = ClientScope.from(clientUuid, assigns);
        clientScopes.saveAll(clientScopeList);
        return null;
    }

    private void validateBeforeSave(UUID clientUuid, List<ClientScopeAssign> assigns) {
        List<UUID> scopeUuids = assigns.stream()
                .map(ClientScopeAssign::scopeUuid)
                .toList();
        Set<UUID> scopeUuidDistinct = new HashSet<>(scopeUuids);
        Boolean diff = scopeUuids.size() != scopeUuidDistinct.size();
        if(diff) {
            throw assignDupInListException();
        }
        Boolean existAllScopes = clientScopes.existsAllScopeUuid(scopeUuids);
        if(!existAllScopes) {
            throw scopeNotFoundDupInListException();
        }
        Boolean duplicate = clientScopes.existsByClientUuidAndScopeUuidIn(clientUuid, scopeUuids);
        if(duplicate) {
            throw assignDupInDbException();
        }
    }

    private ApplicationException clientNotFoundDupInListException() {
        throw new ApplicationException(EnumClientScopeException.CLIENT_NOT_FOUND, EnumClientScopeException.CLIENT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException scopeNotFoundDupInListException() {
        throw new ApplicationException(EnumClientScopeException.SCOPE_NOT_FOUND, EnumClientScopeException.SCOPE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException assignDupInListException() {
        throw new ApplicationException(EnumClientScopeException.ASSIGN_DUPLICATE_IN_LIST, EnumClientScopeException.ASSIGN_DUPLICATE_IN_LIST.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ApplicationException assignDupInDbException() {
        throw new ApplicationException(EnumClientScopeException.ASSIGN_DUPLICATE_IN_DB, EnumClientScopeException.ASSIGN_DUPLICATE_IN_DB.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
