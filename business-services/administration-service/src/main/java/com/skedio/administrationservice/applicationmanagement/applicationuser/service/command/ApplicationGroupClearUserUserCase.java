package com.skedio.administrationservice.applicationmanagement.applicationuser.service.command;

import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupUuid;
import com.skedio.administrationservice.applicationmanagement.applicationuser.repository.ApplicationUserRepository;
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
public class ApplicationGroupClearUserUserCase extends Command<ApplicationGroupUuid, Void> {
    ApplicationUserRepository applicationUsers;

    @Override
    public Void handle(ApplicationGroupUuid command) {
        UUID applicationUuid = command.uuid();
        applicationUsers.clearUserInApplication(applicationUuid);
        return null;
    }
}
