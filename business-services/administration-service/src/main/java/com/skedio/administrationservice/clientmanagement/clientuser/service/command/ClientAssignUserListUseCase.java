package com.skedio.administrationservice.clientmanagement.clientuser.service.command;

import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.argument.ClientUserAssign;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.error.EnumClientUserException;
import com.skedio.administrationservice.clientmanagement.clientuser.repository.ClientUserRepository;
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
public class ClientAssignUserListUseCase extends Command<ClientUserAssign, Void> {
    ClientUserRepository clientUsers;

    @Override
    public Void handle(ClientUserAssign command) {
        UUID clientUuid = command.clientUuid();
        validateClientBeforeSave(clientUuid);
        List<UUID> userUuids = command.userUuids();
        validateUserBeforeSave(clientUuid, userUuids);
        List<ClientUser> clientUserList = command.createList();
        clientUsers.saveAll(clientUserList);
        return null;
    }

    private void validateClientBeforeSave(UUID clientUuid) {
        Boolean exists = clientUsers.existsClientByClientUuidAndEnabledTrue(clientUuid);
        if(!exists){
            throw clientNotFoundException();
        }
    }

    private void validateUserBeforeSave(UUID clientUuid, List<UUID> userUuids) {
        int orgSize = userUuids.size();
        int distinctSize = new HashSet<>(userUuids).size();
        Boolean diff = orgSize != distinctSize;
        if(diff){
            throw duplicateException();
        }
        Boolean exists = clientUsers.existsByClientUuidAndUserUuidIn(clientUuid, userUuids);
        if(exists) {
            throw existException();
        }
    }

    private ApplicationException clientNotFoundException() {
        return new ApplicationException(EnumClientUserException.CLIENT_NOT_FOUND, EnumClientUserException.CLIENT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException duplicateException() {
        return new ApplicationException(EnumClientUserException.USER_DUP_IN_LIST, EnumClientUserException.USER_DUP_IN_LIST.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ApplicationException existException() {
        return new ApplicationException(EnumClientUserException.USER_DUP_IN_DB, EnumClientUserException.USER_DUP_IN_DB.getMessage(), HttpStatus.CONFLICT);
    }
}
