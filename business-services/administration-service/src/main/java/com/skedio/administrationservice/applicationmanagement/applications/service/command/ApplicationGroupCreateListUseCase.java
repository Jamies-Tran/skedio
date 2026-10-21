package com.skedio.administrationservice.applicationmanagement.applications.service.command;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupCreate;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupCreateList;
import com.skedio.administrationservice.applicationmanagement.applications.domain.error.EnumApplicationGroupException;
import com.skedio.administrationservice.applicationmanagement.applications.repository.ApplicationGroupRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationGroupCreateListUseCase extends Command<ApplicationGroupCreateList, List<ApplicationGroup>> {
    ApplicationGroupRepository applicationGroups;

    @Override
    public List<ApplicationGroup> handle(ApplicationGroupCreateList command) {
        List<ApplicationGroupCreate> applicationGroupCreates = command.createList();
        validateBeforeSave(applicationGroupCreates);
        List<ApplicationGroup> applications = ApplicationGroup.from(applicationGroupCreates);
        List<ApplicationGroup> computeBeforeSave = applications.stream()
                .map(ApplicationGroup::computeBeforeSave)
                .toList();
        return applicationGroups.saveAll(computeBeforeSave);
    }

    private void validateBeforeSave(List<ApplicationGroupCreate> creates) {
        Boolean isDuplicateNameInList = isDuplicateNameInList(creates);
        if (isDuplicateNameInList) {
            throw duplicateNameInListException();
        }
        List<String> applicationGroupNames = creates.stream()
                .map(ApplicationGroupCreate::applicationGroupName)
                .toList();
        Boolean isExistsApplicationNames = applicationGroups.existsByApplicationGroupNameIn(applicationGroupNames);
        if (isExistsApplicationNames) {
            throw existsNameException();
        }
    }

    private Boolean isDuplicateNameInList(List<ApplicationGroupCreate> creates) {
        List<String> org = creates.stream()
                .map(ApplicationGroupCreate::applicationGroupName)
                .toList();
        List<String> removeDuplicates = creates.stream()
                .map(ApplicationGroupCreate::applicationGroupName)
                .distinct()
                .toList();
        return org.size() != removeDuplicates.size();
    }

    private ApplicationException duplicateNameInListException() {
        throw new ApplicationException(EnumApplicationGroupException.APP_DUPLICATE_IN_LIST, EnumApplicationGroupException.APP_DUPLICATE_IN_LIST.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ApplicationException existsNameException() {
        throw new ApplicationException(EnumApplicationGroupException.APP_EXISTS, EnumApplicationGroupException.APP_EXISTS.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
