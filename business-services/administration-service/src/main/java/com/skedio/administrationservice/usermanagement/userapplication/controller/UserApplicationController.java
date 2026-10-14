package com.skedio.administrationservice.usermanagement.userapplication.controller;

import com.skedio.administrationservice.usermanagement.userapplication.controller.models.UserApplicationAssignRequest;
import com.skedio.administrationservice.usermanagement.userapplication.controller.models.UserApplicationUnassignRequest;
import com.skedio.administrationservice.usermanagement.userapplication.domain.argument.UserApplicationAssign;
import com.skedio.administrationservice.usermanagement.userapplication.domain.argument.UserApplicationUnassign;
import com.skedio.administrationservice.usermanagement.userapplication.service.command.UserApplicationAssignUseCase;
import com.skedio.administrationservice.usermanagement.userapplication.service.command.UserApplicationUnassignUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user-application")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApplicationController {
    UserApplicationAssignUseCase assignApplicationUseCase;
    UserApplicationUnassignUseCase unassignApplicationUseCase;

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
