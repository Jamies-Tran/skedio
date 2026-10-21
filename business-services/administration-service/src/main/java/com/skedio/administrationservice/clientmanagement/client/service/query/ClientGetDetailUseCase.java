package com.skedio.administrationservice.clientmanagement.client.service.query;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientUuid;
import com.skedio.administrationservice.clientmanagement.client.domain.error.EnumClientException;
import com.skedio.administrationservice.clientmanagement.client.repository.ClientRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Query;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.skedio.corestarter.template.Void;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientGetDetailUseCase extends Query<ClientUuid, Void> {
    ClientRepository clients;

    @Override
    public Void handle(ClientUuid query) {
        UUID clientUuid = query.uuid();
        Optional<Client> find = clients.findByClientUuid(clientUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        Client client = find.get();
        return Void.of(client);
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumClientException.CLIENT_NOT_FOUND, EnumClientException.CLIENT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
