package com.skedio.administrationservice.usermanagement.user.service.query;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.administrationservice.usermanagement.user.domain.argument.UserSearch;
import com.skedio.administrationservice.usermanagement.user.repository.UserRepository;
import com.skedio.corestarter.template.Query;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSearchPageUseCase extends Query<UserSearch, Page<User>> {
    UserRepository users;

    @Override
    public Page<User> handle(UserSearch userSearch) {
        PageRequest pageRequest = userSearch.pageRequest();
        UserSearch computeSearch = userSearch.computeSearch();
        return users.findAll(computeSearch, pageRequest);
    }
}
