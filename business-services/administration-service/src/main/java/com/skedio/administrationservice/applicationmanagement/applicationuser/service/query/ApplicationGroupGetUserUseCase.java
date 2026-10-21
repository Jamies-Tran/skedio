package com.skedio.administrationservice.applicationmanagement.applicationuser.service.query;

import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.ApplicationUser;
import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument.ApplicationGroupGetUser;
import com.skedio.administrationservice.applicationmanagement.applicationuser.repository.ApplicationUserRepository;
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
public class ApplicationGroupGetUserUseCase extends Query<ApplicationGroupGetUser, Page<ApplicationUser>> {
    ApplicationUserRepository applicationGroupUsers;

    @Override
    public Page<ApplicationUser> handle(ApplicationGroupGetUser query) {
        PageRequest pageRequest = query.pageRequest();
        ApplicationGroupGetUser computeSearch = query.computeSearch();
        return applicationGroupUsers.findUserInApplication(computeSearch, pageRequest);
    }
}