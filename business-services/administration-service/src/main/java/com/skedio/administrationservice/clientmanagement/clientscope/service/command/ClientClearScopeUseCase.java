package com.skedio.administrationservice.clientmanagement.clientscope.service.command;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientUuid;
import com.skedio.administrationservice.clientmanagement.clientscope.repository.ClientScopeRepository;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
        clientScopes.deleteAllByClientUuid(clientUuid);
        return null;
    }
}
