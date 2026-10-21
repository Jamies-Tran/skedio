package com.skedio.administrationservice.applicationmanagement.applications.service.command;

import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupUuid;
import com.skedio.administrationservice.applicationmanagement.applications.domain.error.EnumApplicationGroupException;
import com.skedio.administrationservice.applicationmanagement.applications.repository.ApplicationGroupRepository;
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
public class ApplicationGroupDeleteUseCase extends Command<ApplicationGroupUuid, Void> {
    ApplicationGroupRepository applicationGroups;

    @Override
    public Void handle(ApplicationGroupUuid command) {
        UUID applicationGroupUuid = command.uuid();
        allowDelete(applicationGroupUuid);
        applicationGroups.delete(applicationGroupUuid);
        return null;
    }

    private void allowDelete(UUID applicationGroupUuid) {
        Boolean existUserInApp = applicationGroups.existUserInApplication(applicationGroupUuid);
        if (existUserInApp) {
            throw existUserException();
        }
    }

    private ApplicationException existUserException() {
        return new ApplicationException(EnumApplicationGroupException.APP_EXIST_USER, EnumApplicationGroupException.APP_EXIST_USER.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
