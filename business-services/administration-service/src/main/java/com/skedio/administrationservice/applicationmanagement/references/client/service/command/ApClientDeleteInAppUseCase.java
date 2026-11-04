package com.skedio.administrationservice.applicationmanagement.references.client.service.command;

import com.skedio.administrationservice.applicationmanagement.references.client.domain.argument.ApplicationUuid;
import com.skedio.administrationservice.applicationmanagement.references.client.domain.error.EnumApClientException;
import com.skedio.administrationservice.applicationmanagement.references.client.repository.ApClientRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApClientDeleteInAppUseCase extends Command<ApplicationUuid, Void> {
    ApClientRepository clients;

    @Override
    public Void handle(ApplicationUuid command) {
        UUID applicationUuid = command.uuid();
        validateBeforeDelete(applicationUuid);
        clients.deleteAllByApplicationUuid(applicationUuid);
        return null;
    }

    private void validateBeforeDelete(UUID applicationUuid) {
        Boolean exist = clients.existApplicationByApplicationUuid(applicationUuid);
        if(!exist) {
            throw appNotFoundException();
        }
    }

    private ApplicationException appNotFoundException() {
        return new ApplicationException(EnumApClientException.APP_NOT_FOUND, EnumApClientException.APP_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
