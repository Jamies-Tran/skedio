package com.skedio.administrationservice.applicationmanagement.applicationuser.service.command;

import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupUuid;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationGroupClearUserUserCase extends Command<ApplicationGroupUuid, Void> {
    ApplicationUserRepository applicationUsers;

    @Override
    public Void handle(ApplicationGroupUuid command) {
        UUID applicationUuid = command.uuid();
        validateBeforeDelete(applicationUuid);
        applicationUsers.clearUserInApplication(applicationUuid);
        return null;
    }

    private void validateBeforeDelete(UUID applicationUuid) {
        Boolean exist = applicationUsers.existsApplicationByApplicationUuid(applicationUuid);
        if(!exist) {
            throw appNotFoundException();
        }
    }

    private ApplicationException appNotFoundException() {
        return new ApplicationException(EnumApplicationUserException.APPLICATION_NOT_FOUND, EnumApplicationUserException.APPLICATION_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
