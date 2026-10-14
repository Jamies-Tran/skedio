package com.skedio.administrationservice.applicationmanagement.applicationuser.service.command;

import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.ApplicationUser;
import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument.ApplicationUserAddList;
import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.error.EnumApplicationUserException;
import com.skedio.administrationservice.applicationmanagement.applicationuser.repository.ApplicationUserRepository;
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
public class ApplicationGroupAddUserListUseCase extends Command<ApplicationUserAddList, Void> {
    ApplicationUserRepository applicationUsers;

    @Override
    public Void handle(ApplicationUserAddList command) {
        UUID applicationUuid = command.applicationUuid();
        Boolean existsApp = applicationUsers.existsApplicationByApplicationUuid(applicationUuid);
        if(!existsApp) {
            throw appNotFoundException();
        }
        List<UUID> userUuids = command.userUuids();
        Boolean existsAllUser = applicationUsers.existsAllUsersByUserUuidList(userUuids);
        if(!existsAllUser) {
            throw userListNotExistAllException();
        }
        List<UUID> userExistsInApplication = applicationUsers.findAllUserUuidExistInApplication(applicationUuid);
        List<ApplicationUser> users = command.computeSaveListExcludeUserUuid(userExistsInApplication);
        applicationUsers.saveAll(users);
        return null;
    }

    private ApplicationException appNotFoundException() {
        return new ApplicationException(EnumApplicationUserException.APPLICATION_NOT_FOUND, EnumApplicationUserException.APPLICATION_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException userListNotExistAllException() {
        return new ApplicationException(EnumApplicationUserException.USER_NOT_EXIST_ALL, EnumApplicationUserException.USER_NOT_EXIST_ALL.getMessage(), HttpStatus.NOT_FOUND);
    }
}
