package com.skedio.administrationservice.scopemanagement.scope.service.query;

import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeUuid;
import com.skedio.administrationservice.scopemanagement.scope.domain.error.EnumScopeException;
import com.skedio.administrationservice.scopemanagement.scope.repository.ScopeRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Query;
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
public class ScopeGetDetailUseCase extends Query<ScopeUuid, Void> {
    ScopeRepository scopes;

    @Override
    public Void handle(ScopeUuid query) {
        UUID scopeUuid = query.scopeUuid();
        Optional<Scope> find =  scopes.findByScopeUuid(scopeUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        Scope scope = find.get();
        return Void.of(scope);
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumScopeException.SCOPE_NOT_FOUND, EnumScopeException.SCOPE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
