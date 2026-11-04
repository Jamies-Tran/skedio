package com.skedio.administrationservice.applicationmanagement.references.applicationuser.service.query;

import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.ApApplicationUser;
import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.argument.ApplicationGroupGetUser;
import com.skedio.administrationservice.applicationmanagement.references.applicationuser.repository.ApApplicationUserRepository;
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
public class ApApplicationGroupGetUserUseCase extends Query<ApplicationGroupGetUser, Page<ApApplicationUser>> {
    ApApplicationUserRepository applicationGroupUsers;

    @Override
    public Page<ApApplicationUser> handle(ApplicationGroupGetUser query) {
        PageRequest pageRequest = query.pageRequest();
        ApplicationGroupGetUser computeSearch = query.computeSearch();
        return applicationGroupUsers.findUserInApplication(computeSearch, pageRequest);
    }
}