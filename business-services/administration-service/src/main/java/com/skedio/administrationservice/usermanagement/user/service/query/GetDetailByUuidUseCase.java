package com.skedio.administrationservice.usermanagement.user.service.query;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.administrationservice.usermanagement.user.domain.argument.UserGetDetail;
import com.skedio.administrationservice.usermanagement.user.domain.error.EnumUserException;
import com.skedio.administrationservice.usermanagement.user.repository.UserRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Query;
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
public class GetDetailByUuidUseCase extends Query<UserGetDetail, Void> {
    UserRepository users;

    @Override
    public Void handle(UserGetDetail query) {
        UUID userUuid = query.userUuid();
        if (userUuid == null) {
            throw userUuidNullPointerException();
        }
        Optional<User> find = users.findByUserUuid(userUuid);
        if(find.isEmpty()) {
            throw userNotFoundException();
        }
        return Void.of(find.get());
    }

    private ApplicationException userNotFoundException() {
        return new ApplicationException(EnumUserException.USER_NOT_FOUND, EnumUserException.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException userUuidNullPointerException() {
        return new ApplicationException(EnumUserException.USER_UUID_NOT_FOUND, EnumUserException.USER_UUID_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
