package com.skedio.administrationservice.clientmanagement.client.service.query;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientSearch;
import com.skedio.administrationservice.clientmanagement.client.repository.ClientRepository;
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
public class ClientSearchPageUseCase extends Query<ClientSearch, Page<Client>> {
    ClientRepository clients;

    @Override
    public Page<Client> handle(ClientSearch query) {
        PageRequest pageRequest = query.pageRequest();
        ClientSearch computeSearch = query.computeSearch();
        return clients.findAll(computeSearch, pageRequest);
    }
}
