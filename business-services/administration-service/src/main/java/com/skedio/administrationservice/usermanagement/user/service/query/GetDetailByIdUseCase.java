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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetDetailByIdUseCase extends Query<UserGetDetail, Void> {
    UserRepository users;

    @Override
    public Void handle(UserGetDetail query) {
        Long userId = query.userId();
        if (userId == null) {
            throw userIdNullPointerException();
        }
        Optional<User> find = users.findByUserId(userId);
        if(find.isEmpty()) {
            throw userNotFoundException();
        }
        return Void.of(find.get());
    }

    private ApplicationException userNotFoundException() {
        return new ApplicationException(EnumUserException.USER_NOT_FOUND, EnumUserException.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ApplicationException userIdNullPointerException() {
        return new ApplicationException(EnumUserException.USER_ID_NOT_FOUND, EnumUserException.USER_ID_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
