package com.skedio.administrationservice.scopemanagement.scope.service.command;

import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeUpdate;
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

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeUpdateUseCase extends Command<ScopeUpdate, Void> {
    ScopeRepository scopes;

    @Override
    public Void handle(ScopeUpdate command) {
        UUID scopeUuid = command.scopeUuid();
        Optional<Scope> find = scopes.findByScopeUuid(scopeUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        Scope scope = find.get();
        Scope apply = scope.apply(command);
        Scope computeBeforeSave = apply.computeBeforeSave();
        Scope saved = scopes.save(computeBeforeSave);
        return Void.of(saved);
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumScopeException.SCOPE_NOT_FOUND, EnumScopeException.SCOPE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
