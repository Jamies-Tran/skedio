package com.skedio.administrationservice.scopemanagement.scopeclient.service.command;

import com.skedio.administrationservice.scopemanagement.scopeclient.domain.argument.ScopeClientUuid;
import com.skedio.administrationservice.scopemanagement.scopeclient.repository.ScopeClientRepository;
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
public class ScopeUnassignUseCase extends Command<ScopeClientUuid, Void> {
    ScopeClientRepository scopeClients;

    @Override
    public Void handle(ScopeClientUuid command) {
        UUID scopeUuid = command.scopeUuid();
        UUID clientUuid = command.clientUuid();
        scopeClients.deleteByClientUuidAndScopeUuid(clientUuid, scopeUuid);
        return null;
    }
}
