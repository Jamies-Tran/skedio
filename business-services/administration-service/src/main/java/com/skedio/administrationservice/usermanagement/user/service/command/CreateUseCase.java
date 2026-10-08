package com.skedio.administrationservice.usermanagement.user.service.command;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.administrationservice.usermanagement.user.domain.argument.UserCreate;
import com.skedio.administrationservice.usermanagement.user.domain.error.EnumUserException;
import com.skedio.administrationservice.usermanagement.user.repository.UserRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateUseCase extends Command<UserCreate, Void> {
    UserRepository users;
    PasswordEncoder passwordEncoder;

    @Override
    public Void handle(UserCreate command) {
        User user = command.user();
        validate(user);
        user = user.encodePasswordWith(passwordEncoder);
        user = user.computeBeforeSave();
        User createdUser = users.save(user);
        return Void.of(createdUser);
    }

    private  void validate(User user) {
        if(users.existsByUsername(user.username())) {
            throw userDuplicateException("username exists");
        }

        if(users.existsByEmail(user.email())) {
            throw userDuplicateException("email exists");
        }

        if(users.existsByPhone(user.phone())) {
            throw userDuplicateException("phone exists");
        }
    }

    private ApplicationException userDuplicateException(String message) {
        return new ApplicationException(EnumUserException.USER_DUPLICATED, message, HttpStatus.CONFLICT);
    }
}
