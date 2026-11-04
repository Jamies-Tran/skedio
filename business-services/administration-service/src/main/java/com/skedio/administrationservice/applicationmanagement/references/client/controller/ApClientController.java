package com.skedio.administrationservice.applicationmanagement.references.client.controller;

import com.skedio.administrationservice.applicationmanagement.references.client.domain.argument.ApplicationUuid;
import com.skedio.administrationservice.applicationmanagement.references.client.service.command.ApClientDeleteInAppUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api/applications/clients")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApClientController {
    ApClientDeleteInAppUseCase deleteInAppUseCase;

    @DeleteMapping("/all-in-app/{applicationUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInApp(@PathVariable UUID applicationUuid) {
        ApplicationUuid command = new ApplicationUuid(applicationUuid);
        deleteInAppUseCase.handle(command);
    }
}
