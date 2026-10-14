package com.skedio.administrationservice.usermanagement.userapplication.service.command;

import com.skedio.administrationservice.usermanagement.userapplication.domain.UserApplication;
import com.skedio.administrationservice.usermanagement.userapplication.domain.argument.UserApplicationAssign;
import com.skedio.administrationservice.usermanagement.userapplication.domain.error.EnumUserApplicationException;
import com.skedio.administrationservice.usermanagement.userapplication.repository.UserApplicationRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApplicationAssignUseCase extends Command<UserApplicationAssign, Void> {
    UserApplicationRepository userApplications;

    @Override
    public Void handle(UserApplicationAssign command) {
        UUID userUuid = command.userUuid();
        Boolean existUserUuid = userApplications.existsUserByUserUuid(userUuid);
        if(!existUserUuid) {
            throw userNotFountException();
        }
        UUID applicationUuid = command.applicationUuid();
        Boolean existApplicationUuid = userApplications.existsApplicationByApplicationUuid(applicationUuid);
        if(!existApplicationUuid) {
            throw appNotFountException();
        }
        UserApplication assign = UserApplication.from(command);
        validateBeforeSave(assign);
        userApplications.save(assign);
        return null;
    }

    private void validateBeforeSave(UserApplication userApplication) {
        UUID userUuid = userApplication.userUuid();
        UUID applicationUuid = userApplication.applicationUuid();
        boolean exists = userApplications.existsByUserUuidAndApplicationUuid(userUuid, applicationUuid);
        if(exists) {
            throw duplicateException();
        }
    }

    private ApplicationException duplicateException() {
        return new ApplicationException(EnumUserApplicationException.USER_APPLICATION_DUPLICATED, EnumUserApplicationException.USER_APPLICATION_DUPLICATED.getMessage(), HttpStatus.CONFLICT);
    }

    private ApplicationException userNotFountException() {
        return new ApplicationException(EnumUserApplicationException.USER_NOT_FOUND, EnumUserApplicationException.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException appNotFountException() {
        return new ApplicationException(EnumUserApplicationException.APPLICATION_NOT_FOUND, EnumUserApplicationException.APPLICATION_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
