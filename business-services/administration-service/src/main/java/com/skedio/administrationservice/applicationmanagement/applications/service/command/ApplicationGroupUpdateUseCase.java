package com.skedio.administrationservice.applicationmanagement.applications.service.command;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupUpdate;
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

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationGroupUpdateUseCase extends Command<ApplicationGroupUpdate, Void> {
    ApplicationGroupRepository applicationGroups;

    @Override
    public Void handle(ApplicationGroupUpdate command) {
        UUID applicationUuid = command.applicationUuid();
        Optional<ApplicationGroup> find = applicationGroups.findByUuid(applicationUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        ApplicationGroup applicationGroup = find.get();
        validateBeforeUpdate(applicationGroup, command);
        ApplicationGroup apply = applicationGroup.apply(command);
        ApplicationGroup computeBeforeSave = apply.computeBeforeSave();
        ApplicationGroup saved = applicationGroups.save(computeBeforeSave);
        return Void.of(saved);
    }

    private void validateBeforeUpdate(ApplicationGroup exist, ApplicationGroupUpdate update) {
        boolean applicationNameExist = applicationGroups.existsByApplicationName(update.applicationName());
        boolean applicationNameDiff = !Objects.equals(exist.applicationName(), update.applicationName());
        if(applicationNameDiff && applicationNameExist) {
            throw duplicateException();
        }
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumApplicationGroupException.APP_NOT_FOUND, EnumApplicationGroupException.APP_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException duplicateException() {
        return new ApplicationException(EnumApplicationGroupException.APP_EXISTS, EnumApplicationGroupException.APP_EXISTS.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
