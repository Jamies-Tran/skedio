package com.skedio.administrationservice.applicationmanagement.references.applicationuser.service.command;

import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.ApApplicationUser;
import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.argument.ApplicationUserAddList;
import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.error.EnumApplicationUserException;
import com.skedio.administrationservice.applicationmanagement.references.applicationuser.repository.ApApplicationUserRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApApplicationGroupAssignUserUseCase extends Command<ApplicationUserAddList, Void> {
    ApApplicationUserRepository applicationUsers;

    @Override
    public Void handle(ApplicationUserAddList command) {
        UUID applicationUuid = command.applicationUuid();
        validateApplicationBeforeSave(applicationUuid);
        List<UUID> userUuids = command.userUuids();
        validateUserBeforeBeforeSave(userUuids);
        List<UUID> userExistsInApplication = applicationUsers.findAllUserUuidExistInApplication(applicationUuid);
        List<ApApplicationUser> users = command.computeSaveListExcludeUserUuid(userExistsInApplication);
        applicationUsers.saveAll(users);
        return null;
    }

    private void validateApplicationBeforeSave(UUID applicationUuid) {
        Boolean existsApp = applicationUsers.existsApplicationByApplicationUuidAndEnabled(applicationUuid);
        if(!existsApp) {
            throw appNotFoundException();
        }
    }

    private void validateUserBeforeBeforeSave(List<UUID> userUuids) {
        Boolean existsAllUser = applicationUsers.existsAllUsersByUserUuidList(userUuids);
        if(!existsAllUser) {
            throw userListNotExistAllException();
        }
    }

    private ApplicationException appNotFoundException() {
        return new ApplicationException(EnumApplicationUserException.APPLICATION_NOT_FOUND, EnumApplicationUserException.APPLICATION_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException userListNotExistAllException() {
        return new ApplicationException(EnumApplicationUserException.USER_NOT_EXIST_ALL, EnumApplicationUserException.USER_NOT_EXIST_ALL.getMessage(), HttpStatus.NOT_FOUND);
    }
}
