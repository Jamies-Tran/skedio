package com.skedio.administrationservice.clientmanagement.client.service.command;

import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientDeleteAllByApplicationUuid;
import com.skedio.administrationservice.clientmanagement.client.repository.ClientRepository;
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
public class ClientDeleteInApplicationUseCase extends Command<ClientDeleteAllByApplicationUuid, Void> {
    ClientRepository clients;

    @Override
    public Void handle(ClientDeleteAllByApplicationUuid command) {
        UUID applicationUuid = command.applicationUuid();
        clients.deleteAllByApplicationUuid(applicationUuid);
        return null;
    }
}
