package com.skedio.administrationservice.clientmanagement.clientscope.service.query;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.ClientScope;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeSearch;
import com.skedio.administrationservice.clientmanagement.clientscope.repository.ClientScopeRepository;
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
public class ClientScopeSearchScopeInClientUseCase extends Query<ClientScopeSearch, Page<ClientScope>> {
    ClientScopeRepository clientScopes;

    @Override
    public Page<ClientScope> handle(ClientScopeSearch query) {
        PageRequest pageRequest = query.pageRequest();
        ClientScopeSearch computeSearch = query.computeSearch();
        return clientScopes.findAll(computeSearch, pageRequest);
    }
}
