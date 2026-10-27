package com.skedio.administrationservice.scopemanagement.scope.service.command;

import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeCreate;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeCreateUseCase extends Command<ScopeCreate, Void> {
    ScopeRepository scopes;

    @Override
    public Void handle(ScopeCreate command) {
        validateSave(command);
        Scope scope = Scope.from(command);
        Scope computeBeforeSave = scope.computeBeforeSave();
        Scope saved = scopes.save(computeBeforeSave);
        return Void.of(saved);
    }

    private void validateSave(ScopeCreate create) {
        String scopeName = create.scopeName();
        Boolean existScopeName = scopes.existsByScopeName(scopeName);
        if (existScopeName) {
            throw duplicateException();
        }
    }

    private ApplicationException duplicateException() {
        throw new ApplicationException(EnumScopeException.SCOPE_DUPLICATE, EnumScopeException.SCOPE_DUPLICATE.getMessage(), HttpStatus.CONFLICT);
    }
}
