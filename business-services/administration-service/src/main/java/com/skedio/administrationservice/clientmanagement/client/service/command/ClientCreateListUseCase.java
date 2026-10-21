package com.skedio.administrationservice.clientmanagement.client.service.command;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientCreate;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientCreateList;
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

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientCreateListUseCase extends Command<ClientCreateList, List<Client>> {
    ClientRepository clients;

    @Override
    public List<Client> handle(ClientCreateList command) {
        UUID applicationUuid = command.applicationUuid();
        validateApplication(applicationUuid);
        List<ClientCreate> creates = command.creates();
        validateClients(creates);
        List<Client> saveClients = Client.from(applicationUuid, creates);
        saveClients = saveClients.stream()
                .map(Client::computeBeforeSave)
                .toList();
        return clients.saveAll(saveClients);
    }

    private void validateApplication(UUID applicationUuid) {
        Boolean applicationExist = clients.existsApplicationUuidAndEnabled(applicationUuid);
        if (!applicationExist) {
            throw appNotFoundException();
        }
    }

    private void validateClients(List<ClientCreate> creates) {
        List<String> clientNames = creates.stream()
                .map(ClientCreate::clientName)
                .toList();
        int clientNameDistinct = new HashSet<>(clientNames).size();
        int clientNameOrg = clientNames.size();
        boolean clientNameSizeDiff = clientNameDistinct != clientNameOrg;
        if (clientNameSizeDiff) {
            throw clientNameDupInListException();
        }
        boolean clientNameExists = clients.existsByClientNameIn(clientNames);
        if(clientNameExists) {
            throw clientNameDupInDbException();
        }
    }

    private ApplicationException appNotFoundException() {
        throw new ApplicationException(EnumClientException.APP_NOT_FOUND, EnumClientException.APP_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException clientNameDupInListException() {
        throw new ApplicationException(EnumClientException.CLIENT_DUP_IN_LIST, EnumClientException.CLIENT_DUP_IN_LIST.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ApplicationException clientNameDupInDbException() {
        throw new ApplicationException(EnumClientException.CLIENT_DUP_IN_DB, EnumClientException.CLIENT_DUP_IN_DB.getMessage(), HttpStatus.CONFLICT);
    }
}
