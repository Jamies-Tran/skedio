package com.skedio.administrationservice.scopemanagement.scopeclient.controller;

import com.skedio.administrationservice.scopemanagement.scopeclient.controller.models.ScopeUnassignRequest;
import com.skedio.administrationservice.scopemanagement.scopeclient.domain.argument.ScopeClientUuid;
import com.skedio.administrationservice.scopemanagement.scopeclient.service.command.ScopeUnassignUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/scope-client")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeClientController {
    ScopeUnassignUseCase scopeUnassignUseCase;

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void unassign(@RequestBody @Validated ScopeUnassignRequest request) {
        ScopeClientUuid scopeClientUuid = ScopeClientUuid.from(request);
        scopeUnassignUseCase.handle(scopeClientUuid);
    }
}
