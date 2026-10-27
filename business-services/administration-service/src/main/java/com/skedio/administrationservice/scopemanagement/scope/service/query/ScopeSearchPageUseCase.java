package com.skedio.administrationservice.scopemanagement.scope.service.query;

import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeSearch;
import com.skedio.administrationservice.scopemanagement.scope.repository.ScopeRepository;
import com.skedio.corestarter.template.Query;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeSearchPageUseCase extends Query<ScopeSearch, Page<Scope>> {
    ScopeRepository scopes;

    @Override
    public Page<Scope> handle(ScopeSearch query) {
        PageRequest pageRequest = query.pageRequest();
        ScopeSearch search = query.computeSearch();
        return scopes.findAll(search, pageRequest);
    }
}
