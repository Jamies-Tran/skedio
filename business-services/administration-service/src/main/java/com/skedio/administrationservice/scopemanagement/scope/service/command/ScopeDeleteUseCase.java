package com.skedio.administrationservice.scopemanagement.scope.service.command;

import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeUuid;
import com.skedio.administrationservice.scopemanagement.scope.domain.error.EnumScopeException;
import com.skedio.administrationservice.scopemanagement.scope.repository.ScopeRepository;
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
public class ScopeDeleteUseCase extends Command<ScopeUuid, Void> {
    ScopeRepository scopes;

    @Override
    public Void handle(ScopeUuid command) {
        UUID scopeUuid = command.scopeUuid();
        validateBeforeDelete(scopeUuid);
        scopes.deleteByScopeUuid(scopeUuid);
        return null;
    }

    private void validateBeforeDelete(UUID scopeUuid) {
        Boolean inUsed = scopes.existsScopeInClient(scopeUuid);
        if(inUsed) {
            throw inUseException();
        }
    }

    private ApplicationException inUseException() {
        return new ApplicationException(EnumScopeException.SCOPE_IN_USE, EnumScopeException.SCOPE_IN_USE.getMessage(),  HttpStatus.BAD_REQUEST);
    }
}
