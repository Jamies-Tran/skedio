package com.skedio.administrationservice.usermanagement.references.userapplication.service.command;

import com.skedio.administrationservice.usermanagement.references.userapplication.domain.argument.UserApplicationUnassign;
import com.skedio.administrationservice.usermanagement.references.userapplication.repository.UsUserApplicationRepository;
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
public class UsUserApplicationUnassignUseCase extends Command<UserApplicationUnassign, Void> {
    UsUserApplicationRepository userApplications;

    @Override
    public Void handle(UserApplicationUnassign command) {
        UUID userUuid = command.userUuid();
        UUID applicationUuid = command.applicationUuid();
        userApplications.deleteByUserUuidAndApplicationUuid(userUuid, applicationUuid);
        return null;
    }
}
