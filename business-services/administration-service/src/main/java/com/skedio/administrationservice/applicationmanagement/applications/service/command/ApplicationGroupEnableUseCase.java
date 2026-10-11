package com.skedio.administrationservice.applicationmanagement.applications.service.command;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
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

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationGroupDisableUseCase extends Command<ApplicationGroupUuid, Void> {
    ApplicationGroupRepository applicationGroups;

    @Override
    public Void handle(ApplicationGroupUuid command) {
        UUID applicationGroupUuid = command.uuid();
        Optional<ApplicationGroup> find = applicationGroups.findByUuid(applicationGroupUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        ApplicationGroup applicationGroup = find.get();
        applicationGroup = applicationGroup.disable();
        ApplicationGroup disabled = applicationGroups.save(applicationGroup);
        return Void.of(disabled);
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumApplicationGroupException.APP_NOT_FOUND, EnumApplicationGroupException.APP_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
