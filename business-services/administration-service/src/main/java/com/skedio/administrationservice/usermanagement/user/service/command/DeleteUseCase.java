package com.skedio.administrationservice.usermanagement.user.service.command;

import com.skedio.administrationservice.usermanagement.user.domain.argument.UserUuid;
import com.skedio.administrationservice.usermanagement.user.domain.error.EnumUserException;
import com.skedio.administrationservice.usermanagement.user.repository.UserRepository;
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
public class DeleteUseCase extends Command<UserUuid, Void> {
    UserRepository users;

    @Override
    public Void handle(UserUuid command) {
        UUID userUuid = command.userUuid();
        users.delete(userUuid);
        return null;
    }
}
