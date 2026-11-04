package com.skedio.administrationservice.clientmanagement.references.clientscope.service.query;

import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.ClClientScope;
import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.argument.ClientScopeSearch;
import com.skedio.administrationservice.clientmanagement.references.clientscope.repository.ClClientScopeRepository;
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
public class ClientScopeSearchScopeInClientUseCase extends Query<ClientScopeSearch, Page<ClClientScope>> {
    ClClientScopeRepository clientScopes;

    @Override
    public Page<ClClientScope> handle(ClientScopeSearch query) {
        PageRequest pageRequest = query.pageRequest();
        ClientScopeSearch computeSearch = query.computeSearch();
        return clientScopes.findAll(computeSearch, pageRequest);
    }
}
