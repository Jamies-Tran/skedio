package com.skedio.administrationservice.usermanagement.user.service.command;

import com.skedio.administrationservice.usermanagement.user.domain.User;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserInActiveUseCase extends Command<UserUuid, Void> {
    UserRepository users;

    @Override
    public Void handle(UserUuid userUuid) {
        Optional<User> find = users.findByUserUuid(userUuid.userUuid());
        if(find.isEmpty()) {
            throw userNotFound();
        }
        User user = find.get();
        User inactive = user.inactive();
        User saved = users.save(inactive);
        return Void.of(saved);
    }
    private ApplicationException userNotFound() {
        return new ApplicationException(EnumUserException.USER_NOT_FOUND, EnumUserException.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
