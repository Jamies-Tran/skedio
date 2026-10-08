package com.skedio.administrationservice.usermanagement.user.service.command;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.administrationservice.usermanagement.user.domain.argument.UserUpdate;
import com.skedio.administrationservice.usermanagement.user.domain.error.EnumUserException;
import com.skedio.administrationservice.usermanagement.user.repository.UserRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.skedio.corestarter.template.Void;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateUseCase extends Command<UserUpdate, Void> {
    UserRepository users;

    @Override
    public Void handle(UserUpdate command) {
        UUID userUuid = command.userUuid();
        Optional<User> find = users.findByUserUuid(userUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        User user = find.get();
        User update = command.update();
        validateUpdate(user, update);
        user = user.apply(update);
        user = user.computeBeforeSave();
        User saved = users.save(user);
        return Void.of(saved);
    }

    private void validateUpdate(User user, User update) {
        if(!Objects.equals(user.username(), update.username())) {
            if(users.existsByUsername(update.username())) {
                throw duplicateException();
            }
        }

        if(!Objects.equals(user.email(), update.email())) {
            if(users.existsByEmail(update.email())) {
                throw duplicateException();
            }
        }

        if(!Objects.equals(user.phone(), update.phone())) {
            if(users.existsByPhone(update.phone())) {
                throw duplicateException();
            }
        }
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumUserException.USER_NOT_FOUND, EnumUserException.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException duplicateException() {
        throw new ApplicationException(EnumUserException.USER_DUPLICATED, EnumUserException.USER_DUPLICATED.getMessage(), HttpStatus.CONFLICT);
    }
}
