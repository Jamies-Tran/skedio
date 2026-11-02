package com.skedio.administrationservice.clientmanagement.clientscope.service.command;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientUuid;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientClearScopeUseCase extends Command<ClientUuid, Void> {
    ClientScopeRepository clientScopes;

    @Override
    public Void handle(ClientUuid command) {
        UUID clientUuid = command.clientUuid();
        validateBeforeDelete(clientUuid);
        clientScopes.deleteAllByClientUuid(clientUuid);
        return null;
    }

    private void validateBeforeDelete(UUID clientUuid) {
        Boolean exists = clientScopes.existsClientByClientUuid(clientUuid);
        if(!exists) {
            throw clientNotFoundException();
        }
    }

    private ApplicationException clientNotFoundException() {
        throw new ApplicationException(EnumClientScopeException.CLIENT_NOT_FOUND, EnumClientScopeException.CLIENT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
