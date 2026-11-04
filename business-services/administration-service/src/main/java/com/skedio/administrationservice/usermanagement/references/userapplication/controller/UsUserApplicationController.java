package com.skedio.administrationservice.usermanagement.references.userapplication.controller;

import com.skedio.administrationservice.usermanagement.references.userapplication.controller.models.UserApplicationAssignRequest;
import com.skedio.administrationservice.usermanagement.references.userapplication.controller.models.UserApplicationUnassignRequest;
import com.skedio.administrationservice.usermanagement.references.userapplication.domain.argument.UserApplicationAssign;
import com.skedio.administrationservice.usermanagement.references.userapplication.domain.argument.UserApplicationUnassign;
import com.skedio.administrationservice.usermanagement.references.userapplication.service.command.UsUserApplicationAssignUseCase;
import com.skedio.administrationservice.usermanagement.references.userapplication.service.command.UsUserApplicationUnassignUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users/user-application")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsUserApplicationController {
    UsUserApplicationAssignUseCase assignApplicationUseCase;
    UsUserApplicationUnassignUseCase unassignApplicationUseCase;

    @PostMapping("/assign-to-application")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void assignToApplicationGroup(@RequestBody @Validated UserApplicationAssignRequest request) {
        UserApplicationAssign assign = UserApplicationAssign.from(request);
        assignApplicationUseCase.handle(assign);
    }

    @PostMapping("/unassign-from-application")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void unassignToApplicationGroup(@RequestBody @Validated UserApplicationUnassignRequest request) {
        UserApplicationUnassign unassign = UserApplicationUnassign.from(request);
        unassignApplicationUseCase.handle(unassign);
    }
}
