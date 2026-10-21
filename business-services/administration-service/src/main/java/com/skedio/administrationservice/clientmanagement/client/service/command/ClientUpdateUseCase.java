package com.skedio.administrationservice.clientmanagement.client.service.command;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientUpdate;
import com.skedio.administrationservice.clientmanagement.client.domain.error.EnumClientException;
import com.skedio.administrationservice.clientmanagement.client.repository.ClientRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientUpdateUseCase extends Command<ClientUpdate, Void> {
    ClientRepository clients;

    @Override
    public Void handle(ClientUpdate command) {
        UUID clientUuid = command.clientUuid();
        Optional<Client> find = clients.findByClientUuid(clientUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        Client client = find.get();
        validateUpdate(client, command);
        Client updateClient = client.apply(command);
        Client computeBeforeSave = updateClient.computeBeforeSave();
        Client saved = clients.save(computeBeforeSave);
        return Void.of(saved);
    }

    private void validateUpdate(Client exist, ClientUpdate update) {
        String existName = exist.clientName();
        String updateName = update.clientName();
        if(!Objects.equals(existName, updateName)) {
            Boolean existClientName = clients.existByClientName(updateName);
            if(existClientName) {
                throw duplicateException();
            }
        }
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumClientException.CLIENT_NOT_FOUND, EnumClientException.CLIENT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException duplicateException() {
        return new ApplicationException(EnumClientException.CLIENT_DUP_IN_DB, EnumClientException.CLIENT_DUP_IN_DB.getMessage(), HttpStatus.CONFLICT);
    }
}
