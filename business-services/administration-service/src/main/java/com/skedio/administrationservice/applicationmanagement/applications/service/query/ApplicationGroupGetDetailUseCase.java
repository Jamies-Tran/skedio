package com.skedio.administrationservice.applicationmanagement.applications.service.query;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupUuid;
import com.skedio.administrationservice.applicationmanagement.applications.domain.error.EnumApplicationGroupException;
import com.skedio.administrationservice.applicationmanagement.applications.repository.ApplicationGroupRepository;
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
public class ApplicationGroupGetDetailUseCase extends Query<ApplicationGroupUuid, Void> {
    ApplicationGroupRepository applicationGroups;

    @Override
    public Void handle(ApplicationGroupUuid query) {
        UUID applicationUuid = query.uuid();
        Optional<ApplicationGroup> find = applicationGroups.findByUuid(applicationUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        ApplicationGroup applicationGroup = find.get();
        return Void.of(applicationGroup);
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumApplicationGroupException.APP_NOT_FOUND, EnumApplicationGroupException.APP_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
