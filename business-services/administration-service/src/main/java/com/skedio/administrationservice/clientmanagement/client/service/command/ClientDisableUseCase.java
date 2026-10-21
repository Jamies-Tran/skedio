package com.skedio.administrationservice.clientmanagement.client.service.command;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientUuid;
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

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientDisableUseCase extends Command<ClientUuid, Void> {
    ClientRepository clients;

    @Override
    public Void handle(ClientUuid command) {
        UUID clientUuid = command.uuid();
        Optional<Client> find = clients.findByClientUuid(clientUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        Client client = find.get();
        Client disabled = client.disable();
        Client saved = clients.save(disabled);
        return Void.of(saved);
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumClientException.CLIENT_NOT_FOUND, EnumClientException.CLIENT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
