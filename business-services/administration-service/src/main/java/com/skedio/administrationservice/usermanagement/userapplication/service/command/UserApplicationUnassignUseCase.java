package com.skedio.administrationservice.usermanagement.userapplication.service.command;

import com.skedio.administrationservice.usermanagement.userapplication.domain.argument.UserApplicationUnassign;
import com.skedio.administrationservice.usermanagement.userapplication.repository.UserApplicationRepository;
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
public class UserApplicationUnassignUseCase extends Command<UserApplicationUnassign, Void> {
    UserApplicationRepository userApplications;

    @Override
    public Void handle(UserApplicationUnassign command) {
        UUID userUuid = command.userUuid();
        UUID applicationUuid = command.applicationUuid();
        userApplications.deleteByUserUuidAndApplicationUuid(userUuid, applicationUuid);
        return null;
    }
}
