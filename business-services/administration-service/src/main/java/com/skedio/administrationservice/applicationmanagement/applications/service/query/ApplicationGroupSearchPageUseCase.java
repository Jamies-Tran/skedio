package com.skedio.administrationservice.applicationmanagement.applications.service.query;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupSearch;
import com.skedio.administrationservice.applicationmanagement.applications.repository.ApplicationGroupRepository;
import com.skedio.corestarter.template.Query;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationGroupSearchPageUseCase extends Query<ApplicationGroupSearch, Void> {
    ApplicationGroupRepository applicationGroups;

    @Override
    public Void handle(ApplicationGroupSearch query) {
        PageRequest pageRequest = query.pageRequest();
        query = query.computeSearch();
        Page<ApplicationGroup> page = applicationGroups.findAll(query, pageRequest);
        return Void.of(page);
    }
}
