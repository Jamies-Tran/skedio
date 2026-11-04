package com.skedio.administrationservice.clientmanagement.references.clientuser.service.command;

import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument.ClientUuid;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.error.EnumClientUserException;
import com.skedio.administrationservice.clientmanagement.references.clientuser.repository.ClClientUserRepository;
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
public class ClientClearUserUseCase extends Command<ClientUuid, Void> {
    ClClientUserRepository clientUsers;

    @Override
    public Void handle(ClientUuid command) {
        UUID clientUuid = command.uuid();
        validateBeforeDelete(clientUuid);
        clientUsers.deleteAllByClientUuid(clientUuid);
        return null;
    }

    private void validateBeforeDelete(UUID clientUuid) {
        Boolean exist = clientUsers.existsClientByClientUuid(clientUuid);
        if(!exist) {
            throw clientNotFoundException();
        }
    }

    private ApplicationException clientNotFoundException() {
        return new ApplicationException(EnumClientUserException.CLIENT_NOT_FOUND, EnumClientUserException.CLIENT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
